package com.sedsoftware.core.presentation.custom.view.transitions

import android.view.View
import at.wirecube.additiveanimations.additive_animator.AdditiveAnimator
import at.wirecube.additiveanimations.additive_animator.AnimationEndListener
import com.sedsoftware.core.presentation.custom.animation.ViewStateTransition
import com.sedsoftware.core.presentation.custom.animation.ViewStateTransition.Callback
import com.sedsoftware.core.presentation.custom.view.DownloadButton
import com.sedsoftware.core.presentation.extension.hide
import com.sedsoftware.core.presentation.extension.show

class ProgressToCompletedTransition(override val from: View?, override val to: View?) : ViewStateTransition {

    override fun run(callback: Callback) {
        animateBoth(callback)
    }

    private fun animateBoth(callback: Callback) {
        to?.translationY = DownloadButton.VERTICAL_VIEW_SHIFT
        AdditiveAnimator()
            .setDuration(DownloadButton.ANIMATION_DURATION)
            .target(from).translationY(-DownloadButton.VERTICAL_VIEW_SHIFT)
            .target(to).translationY(0f)
            .addStartAction {
                to?.show()
            }
            .addEndAction {
                from?.hide()
                callback.completed()
            }
            .start()
    }
}
