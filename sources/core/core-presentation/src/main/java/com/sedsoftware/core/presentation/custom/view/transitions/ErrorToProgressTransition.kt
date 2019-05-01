package com.sedsoftware.core.presentation.custom.view.transitions

import android.view.View
import com.sedsoftware.core.presentation.custom.animation.ViewStateTransition
import com.sedsoftware.core.presentation.custom.animation.ViewStateTransition.Callback

class ErrorToProgressTransition(override val from: View?, override val to: View?) : ViewStateTransition {

    override fun run(callback: Callback) {
        animateBoth(callback)
    }

    private fun animateBoth(callback: Callback) {
//        to?.translationY = DownloadButton.VERTICAL_VIEW_SHIFT
//        AdditiveAnimator()
//            .setDuration(DownloadButton.ANIMATION_DURATION)
//            .targets(from, to)
//            .translationYBy(-DownloadButton.VERTICAL_VIEW_SHIFT)
//            .addStartAction {
//                to?.show()
//            }
//            .addEndAction {
//                from?.hide()
//                callback.completed()
//            }
//            .start()
    }
}
