package com.sedsoftware.screens.market

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import androidx.transition.ArcMotion
import com.sedsoftware.core.presentation.base.BaseFragment
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_pair_view.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                setupViews()
                add_pair_view.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun setupViews() {
        defaultDialogCenterX = centerX(add_pair_view)
        defaultDialogCenterY = centerY(add_pair_view)
        defaultFabCenterX = centerX(market_fab)
        defaultFabCenterY = centerY(market_fab)

        dialogTranslationX = defaultFabCenterX - defaultDialogCenterX
        dialogTranslationY = defaultFabCenterY - defaultDialogCenterY

        add_pair_view.translationX = dialogTranslationX
        add_pair_view.translationY = dialogTranslationY

        market_fab.setOnClickListener {
            val set = AnimatorSet()
            set.play(getFabAnimator())
            set.start()
        }
    }

    private fun centerX(view: View) = view.x + view.width / 2

    private fun centerY(view: View) = view.y + view.height / 2

    private fun getFabAnimator(): Animator =
        ObjectAnimator.ofFloat(
            market_fab,
            View.TRANSLATION_X,
            View.TRANSLATION_Y,
            ArcMotion().getPath(0f, 0f, -dialogTranslationX, -dialogTranslationY)
        ).apply {
            interpolator = fastOutLinearInInterpolator
            duration = REVEAL_ANIMATION_DURATION
        }

    private companion object {
        const val REVEAL_ANIMATION_DURATION = 1000L
    }
}
