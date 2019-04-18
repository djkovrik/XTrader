package com.sedsoftware.core.presentation.custom.view

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.sedsoftware.core.presentation.R
import com.sedsoftware.core.presentation.custom.DownloadState
import com.sedsoftware.core.presentation.custom.DownloadState.AVAILABLE
import com.sedsoftware.core.presentation.custom.DownloadState.COMPLETED
import com.sedsoftware.core.presentation.custom.DownloadState.ERROR
import com.sedsoftware.core.presentation.custom.DownloadState.IN_PROGRESS
import com.sedsoftware.core.presentation.custom.animation.ViewStateTransitionAnimator
import com.sedsoftware.core.presentation.custom.view.transitions.AvailableToProgressTransition
import com.sedsoftware.core.presentation.custom.view.transitions.ErrorToProgressTransition
import com.sedsoftware.core.presentation.custom.view.transitions.ProgressToCompletedTransition
import com.sedsoftware.core.presentation.custom.view.transitions.ProgressToErrorTransition
import com.sedsoftware.core.presentation.extension.show
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_download_button.button
import kotlinx.android.synthetic.main.view_download_button.completed
import kotlinx.android.synthetic.main.view_download_button.error
import kotlinx.android.synthetic.main.view_download_button.error_image
import kotlinx.android.synthetic.main.view_download_button.error_text
import kotlinx.android.synthetic.main.view_download_button.progress

class DownloadButton : ViewStateTransitionAnimator, LayoutContainer {

    companion object {
        const val ANIMATION_DURATION = 200L
        const val VERTICAL_VIEW_SHIFT = 100f
    }

    constructor(context: Context) : super(context) {
        LayoutInflater.from(context).inflate(R.layout.view_download_button, this, true)
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_download_button, this, true)
        init(attrs)
    }

    var clickListener: () -> Unit = {}
        set(value) {
            field = value
            button?.setOnClickListener { field.invoke() }
            error_text?.setOnClickListener { field.invoke() }
            error_image?.setOnClickListener { field.invoke() }
        }


    private lateinit var views: Map<DownloadState, View>

    private var currentState: DownloadState? = null
    private var textAvailable: String? = null
    private var textInProgress: String? = null
    private var textCompleted: String? = null
    private var textError: String? = null
    private var colorInProgress: Int? = null
    private var colorCompleted: Int? = null
    private var colorError: Int? = null

    override val containerView: View?
        get() = this

    private fun init(attrs: AttributeSet?) {
        val typedArray = context?.obtainStyledAttributes(attrs, R.styleable.DownloadButton)

        textAvailable = typedArray?.getString(R.styleable.DownloadButton_text_available)
        textInProgress = typedArray?.getString(R.styleable.DownloadButton_text_in_progress)
        textCompleted = typedArray?.getString(R.styleable.DownloadButton_text_completed)
        textError = typedArray?.getString(R.styleable.DownloadButton_text_error)

        colorInProgress = typedArray?.getInt(R.styleable.DownloadButton_color_in_progress, 0)
        colorCompleted = typedArray?.getInt(R.styleable.DownloadButton_color_completed, 0)
        colorError = typedArray?.getInt(R.styleable.DownloadButton_color_error, 0)

        typedArray?.recycle()

        textAvailable?.let { button.text = it }
        textInProgress?.let { progress.text = it }
        textCompleted?.let { completed.text = it }
        textError?.let { error_text.text = it }

        colorInProgress?.let { progress.setTextColor(it) }
        colorCompleted?.let { completed.setTextColor(it) }
        colorError?.let { error_text.setTextColor(it) }
        colorError?.let { error_image.setColorFilter(it, PorterDuff.Mode.SRC_IN) }

        views = mapOf(
            AVAILABLE to button,
            IN_PROGRESS to progress,
            COMPLETED to completed,
            ERROR to error
        )
    }

    fun setState(state: DownloadState) {
        when {
            currentState == null -> {
                currentState = state
                views[state]?.show()
            }
            currentState == AVAILABLE && state == IN_PROGRESS -> {
                perform(AvailableToProgressTransition(views[AVAILABLE], views[IN_PROGRESS]))
            }
            currentState == IN_PROGRESS && state == COMPLETED -> {
                perform(ProgressToCompletedTransition(views[IN_PROGRESS], views[COMPLETED]))
            }
            currentState == IN_PROGRESS && state == ERROR -> {
                perform(ProgressToErrorTransition(views[IN_PROGRESS], views[ERROR]))
            }
            currentState == ERROR && state == IN_PROGRESS -> {
                perform(ErrorToProgressTransition(views[ERROR], views[IN_PROGRESS]))
            }
        }
        currentState = state
    }
}
