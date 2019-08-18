package com.sedsoftware.screens.market

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.extension.centerX
import com.sedsoftware.core.presentation.extension.centerY
import kotlinx.android.synthetic.main.fragment_market_screen.*
import kotlinx.android.synthetic.main.view_add_pair.*

class MarketScreenFragment : BaseFragment() {

    private val fastOutLinearInInterpolator: Interpolator by lazy {
        AnimationUtils.loadInterpolator(context, android.R.interpolator.fast_out_linear_in)
    }

    private var defaultDialogCenterX = 0f
    private var defaultDialogCenterY = 0f
    private var dialogTranslationX = 0f
    private var dialogTranslationY = 0f
    private var defaultFabCenterX = 0f
    private var defaultFabCenterY = 0f

    override fun getLayoutResId(): Int =
        R.layout.fragment_market_screen

    override fun inject() {
    }

    private var dialogExpanded: Boolean = false
        get() {
            return arguments?.getBoolean(DIALOG_STATE_KEY) ?: false
        }
        set(value) {
            arguments = Bundle().apply { putBoolean(DIALOG_STATE_KEY, value) }
            field = value
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_pair_view.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                setupViewParams()
                setupFabDialogState()
                add_pair_view.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun setupViewParams() {
        defaultDialogCenterX = add_pair_view.centerX()
        defaultDialogCenterY = add_pair_view.centerY()
        defaultFabCenterX = market_fab.centerX()
        defaultFabCenterY = market_fab.centerY()

        dialogTranslationX = defaultFabCenterX - defaultDialogCenterX
        dialogTranslationY = defaultFabCenterY - defaultDialogCenterY
    }

    private fun setupFabDialogState() {
        if (dialogExpanded) {
            add_pair_view.translationX = 0f
            add_pair_view.translationY = 0f

            add_pair_view.isVisible = true
            overlay_global.isVisible = true

            market_fab.isGone = true
            overlay.isGone = true
            overlay_icon.isGone = true
        } else {
            add_pair_view.translationX = dialogTranslationX
            add_pair_view.translationY = dialogTranslationY

            add_pair_view.isGone = true
            overlay_global.isGone = true
        }
    }

//    add_pair_view.translationX = dialogTranslationX
//    add_pair_view.translationY = dialogTranslationY
//    add_pair_view.gone()
//    overlay.gone()
//    overlay_icon.gone()
//    overlay_global.gone()
//
//    market_fab.setOnClickListener {
//        val set = AnimatorSet()
//        set.playTogether(
//            getDialogArcPathAnimator(),
//            getFabArcPathAnimator(),
//            getOverlayShow(),
//            getHideAlphaAnimator(market_fab),
//            getCircularRevealAnimator(),
//            getHideAlphaAnimator(overlay),
//            getHideAlphaAnimator(overlay_icon)
//        )
//        set.start()
//    }

    //    private fun getFabArcPathAnimator(): Animator =
//        ObjectAnimator.ofFloat(
//            market_fab,
//            View.TRANSLATION_X,
//            View.TRANSLATION_Y,
//            ArcMotion().getPath(0f, 0f, -dialogTranslationX, -dialogTranslationY)
//        ).apply {
//            interpolator = fastOutLinearInInterpolator
//            duration = REVEAL_ANIMATION_DURATION
//        }
//
//    private fun getHideAlphaAnimator(target: View): Animator =
//        ObjectAnimator.ofFloat(target, View.ALPHA, 1f, 0f)
//            .apply {
//                interpolator = fastOutLinearInInterpolator
//                duration = REVEAL_ANIMATION_DURATION
//                startDelay = OVERLAY_ANIMATION_DELAY
//            }
//
//    private fun getOverlayShow(): Animator =
//        ObjectAnimator.ofFloat(overlay_global, View.ALPHA, 0f, 1f)
//            .apply {
//                interpolator = fastOutLinearInInterpolator
//                duration = REVEAL_ANIMATION_DURATION
//                addListener(object : AnimatorListener {
//                    override fun onAnimationRepeat(animation: Animator?) {
//
//                    }
//
//                    override fun onAnimationEnd(animation: Animator?) {
//                        overlay_global.alpha = 1f
//                        overlay_icon.hide()
//                    }
//
//                    override fun onAnimationCancel(animation: Animator?) {
//
//                    }
//
//                    override fun onAnimationStart(animation: Animator?) {
//                        overlay_global.alpha = 0f
//                        overlay_global.show()
//                    }
//                })
//        }
//
//    private fun getDialogArcPathAnimator(): Animator =
//        ObjectAnimator.ofFloat(
//            add_pair_view,
//            View.TRANSLATION_X,
//            View.TRANSLATION_Y,
//            ArcMotion().getPath(dialogTranslationX, dialogTranslationY, 0f, 0f)
//        ).apply {
//            interpolator = fastOutLinearInInterpolator
//            duration = REVEAL_ANIMATION_DURATION
//        }
//
//    private fun getCircularRevealAnimator(): Animator =
//        ViewAnimationUtils.createCircularReveal(
//            add_pair_view,
//            add_pair_view.width / 2,
//            add_pair_view.height / 2,
//            market_fab.width / 2f,
//            add_pair_view.height.toFloat()
//        ).apply {
//            interpolator = fastOutLinearInInterpolator
//            duration = REVEAL_ANIMATION_DURATION
//            startDelay = CIRCULAR_ANIMATION_DELAY
//            addListener(object : AnimatorListener {
//                override fun onAnimationRepeat(animation: Animator?) {
//
//                }
//
//                override fun onAnimationEnd(animation: Animator?) {
//                    market_fab.alpha = 0f
//                    overlay.hide()
//                    overlay_icon.hide()
//                }
//
//                override fun onAnimationCancel(animation: Animator?) {
//
//                }
//
//                override fun onAnimationStart(animation: Animator?) {
//                    add_pair_view.show()
//                    overlay.show()
//                    market_fab.visibility = View.GONE
//                    overlay_icon.show()
//                }
//            })
//        }
//
    private companion object {
        const val DIALOG_STATE_KEY = "DIALOG_STATE_KEY"
        const val REVEAL_ANIMATION_DURATION = 250L
        const val CIRCULAR_ANIMATION_DELAY = 100L
        const val OVERLAY_ANIMATION_DELAY = 150L
    }
}
