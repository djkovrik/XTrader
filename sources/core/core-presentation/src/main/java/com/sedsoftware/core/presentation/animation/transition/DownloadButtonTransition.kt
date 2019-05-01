package com.sedsoftware.core.presentation.animation.transition

import android.view.View
import com.sedsoftware.core.presentation.animation.ViewStateTransition
import com.sedsoftware.core.presentation.animation.ViewStateTransition.Callback
import com.sedsoftware.core.presentation.extension.addEndAction
import com.sedsoftware.core.presentation.extension.addStartEndActions
import com.sedsoftware.core.presentation.extension.hide
import com.sedsoftware.core.presentation.extension.show

class DownloadButtonTransition(
    private val from: View?,
    private val to: View?,
    private val viewShift: Float
) : ViewStateTransition {

    override fun run(callback: Callback) {

        to?.translationY = viewShift

        from?.animate()
            ?.translationYBy(-viewShift)
            ?.setDuration(ANIMATION_DURATION)
            ?.addEndAction { from.hide() }

        to?.animate()
            ?.translationYBy(-viewShift)
            ?.setDuration(ANIMATION_DURATION)
            ?.addStartEndActions(
                startWith = { to.show() },
                endWith = { callback.completed() }
            )
            ?.start()

    }

    private companion object {
        const val ANIMATION_DURATION = 200L
    }
}
