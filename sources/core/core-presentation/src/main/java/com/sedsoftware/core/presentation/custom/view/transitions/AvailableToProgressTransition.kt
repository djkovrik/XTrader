package com.sedsoftware.core.presentation.custom.view.transitions

import android.view.View
import at.wirecube.additiveanimations.additive_animator.AdditiveAnimator
import at.wirecube.additiveanimations.additive_animator.AnimationEndListener
import com.sedsoftware.core.presentation.custom.animation.ViewStateTransition
import com.sedsoftware.core.presentation.custom.animation.ViewStateTransition.Callback
import com.sedsoftware.core.presentation.custom.animation.ViewStateTransitionAnimator
import com.sedsoftware.core.presentation.extension.hide
import com.sedsoftware.core.presentation.extension.show

class AvailableToProgressTransition(override val from: View?, override val to: View?) : ViewStateTransition {

    override fun run(callback: Callback) {
        animateFrom(callback)
    }

    private fun animateFrom(callback: Callback) {
        AdditiveAnimator.animate(from)
            .setDuration(ViewStateTransitionAnimator.ANIMATION_DURATION)
            .alpha(0f)
            .scale(0f)
            .addEndAction(object : AnimationEndListener() {
                override fun onAnimationEnd(wasCancelled: Boolean) {
                    from?.hide()
                    to?.alpha = 0f
                    animateTo(callback)
                }
            })
            .start()
    }

    private fun animateTo(callback: Callback) {
        AdditiveAnimator.animate(to)
            .setDuration(ViewStateTransitionAnimator.ANIMATION_DURATION)
            .addStartAction {
                to?.show()
            }
            .alpha(1f)
            .addEndAction(object : AnimationEndListener() {
                override fun onAnimationEnd(wasCancelled: Boolean) {
                    callback.completed()
                }
            })
            .start()
    }
}
