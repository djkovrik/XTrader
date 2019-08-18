package com.sedsoftware.screens.market

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.transition.ArcMotion
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.extension.addEndAction
import com.sedsoftware.core.presentation.extension.addStartEndActions
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

        market_fab.setOnClickListener { changeDialogExpandState() }
        overlay_global.setOnClickListener { changeDialogExpandState() }
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

    private fun changeDialogExpandState() {
        val set = AnimatorSet()
        set.playTogether(
            getFabArcPathAnimator(),
            getDialogArcPathAnimator(),
            getGlobalOverlayAnimator(),
            getCircularRevealAnimator(),
            getOverlayAlphaAnimator(),
            getOverlayIconAlphaAnimator()
        )
        set.addEndAction { dialogExpanded = !dialogExpanded }.start()
    }

    private fun getFabArcPathAnimator(): Animator {
        val startX = if (dialogExpanded) -dialogTranslationX else 0f
        val startY = if (dialogExpanded) -dialogTranslationY else 0f
        val endX = if (dialogExpanded) 0f else -dialogTranslationX
        val endY = if (dialogExpanded) 0f else -dialogTranslationY

        return ObjectAnimator.ofFloat(
            market_fab,
            View.TRANSLATION_X,
            View.TRANSLATION_Y,
            ArcMotion().getPath(startX, startY, endX, endY)
        )
            .applyDefaultParams()
            .addStartEndActions(
                startWith = {
                    if (!dialogExpanded) {
                        market_fab.isGone = true
                        add_pair_view.isVisible = true
                        overlay.isVisible = true
                        overlay_icon.isVisible = true
                    }
                },
                endWith = {
                    if (dialogExpanded) {
                        market_fab.isVisible = true
                        add_pair_view.isGone = true
                        overlay.isGone = true
                        overlay_icon.isGone = true
                    }
                })
    }

    private fun getDialogArcPathAnimator(): Animator {
        val startX = if (dialogExpanded) 0f else dialogTranslationX
        val startY = if (dialogExpanded) 0f else dialogTranslationY
        val endX = if (dialogExpanded) dialogTranslationX else 0f
        val endY = if (dialogExpanded) dialogTranslationY else 0f


        return ObjectAnimator.ofFloat(
            add_pair_view,
            View.TRANSLATION_X,
            View.TRANSLATION_Y,
            ArcMotion().getPath(startX, startY, endX, endY)
        ).applyDefaultParams()
    }

    private fun getOverlayAlphaAnimator(): Animator {
        val startValue = if (dialogExpanded) 0f else 1f
        val endValue = if (dialogExpanded) 1f else 0f

        return ObjectAnimator.ofFloat(overlay, View.ALPHA, startValue, endValue)
            .applyDefaultParams()
            .addEndAction {
                overlay.alpha = endValue
                overlay.isGone = dialogExpanded
            }
            .apply {
                startDelay = if (!dialogExpanded) DIALOG_OVERLAY_ANIMATION_DELAY else 0L
            }
    }

    private fun getOverlayIconAlphaAnimator(): Animator {
        val startValue = if (dialogExpanded) 0f else 1f
        val endValue = if (dialogExpanded) 1f else 0f

        return ObjectAnimator.ofFloat(overlay_icon, View.ALPHA, startValue, endValue)
            .applyDefaultParams()
            .addEndAction {
                overlay_icon.alpha = endValue
                overlay_icon.isGone = dialogExpanded
            }
    }

    private fun getGlobalOverlayAnimator(): Animator {
        val startValue = if (dialogExpanded) 1f else 0f
        val endValue = if (dialogExpanded) 0f else 1f

        return ObjectAnimator.ofFloat(overlay_global, View.ALPHA, startValue, endValue)
            .applyDefaultParams()
            .addStartEndActions(
                startWith = {
                    overlay_global.alpha = startValue
                    if (!dialogExpanded) overlay_global.isVisible = true
                },
                endWith = {
                    overlay_global.alpha = endValue
                    if (dialogExpanded) overlay_global.isGone = true
                }
            )
    }

    private fun getCircularRevealAnimator(): Animator {
        val startRadius = if (dialogExpanded) add_pair_view.height.toFloat() else market_fab.width / 2f
        val endRadius = if (dialogExpanded) market_fab.width / 2f else add_pair_view.height.toFloat()

        return ViewAnimationUtils.createCircularReveal(
            add_pair_view,
            add_pair_view.width / 2,
            add_pair_view.height / 2,
            startRadius,
            endRadius
        ).applyDefaultParams()
    }

    private fun Animator.applyDefaultParams(startDelay: Long = 0) =
        apply {
            this.interpolator = fastOutLinearInInterpolator
            this.duration = DIALOG_ANIMATION_DURATION
            this.startDelay = startDelay
        }

    private companion object {
        const val DIALOG_STATE_KEY = "DIALOG_STATE_KEY"
        const val DIALOG_ANIMATION_DURATION = 2500L
        const val DIALOG_OVERLAY_ANIMATION_DELAY = 1500L
    }
}
