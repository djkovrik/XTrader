package com.sedsoftware.core.presentation.custom.view.transitions

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.view.View
import com.sedsoftware.core.presentation.custom.animation.ViewStateTransition
import com.sedsoftware.core.presentation.custom.animation.ViewStateTransition.Callback
import com.sedsoftware.core.presentation.extension.gone
import com.sedsoftware.core.presentation.extension.show

class ProgressToCompletedTransition(override val from: View?, override val to: View?) : ViewStateTransition {

    override fun run(callback: Callback) {
        hideFromView(callback)
    }

    private fun hideFromView(callback: Callback) {
        from?.animate()
            ?.scaleX(0f)
            ?.scaleY(0f)
            ?.setDuration(500)
            ?.setListener(object : AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    from.gone()
                    showToView(callback)
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }
            })
            ?.start()
    }

    private fun showToView(callback: Callback) {
        to?.animate()
            ?.alpha(1f)
            ?.setDuration(500)
            ?.setListener(object : AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    callback.completed()
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                    to.show()
                }
            })
            ?.start()
    }
}
