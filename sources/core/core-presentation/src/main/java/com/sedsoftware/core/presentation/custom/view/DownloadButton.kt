package com.sedsoftware.core.presentation.custom.view

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.sedsoftware.core.presentation.R
import com.sedsoftware.core.presentation.custom.DownloadState
import com.sedsoftware.core.presentation.custom.animation.ViewStateTransitionAnimator
import com.sedsoftware.core.presentation.extension.gone
import com.sedsoftware.core.presentation.extension.show
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_download_button.button
import kotlinx.android.synthetic.main.view_download_button.completed
import kotlinx.android.synthetic.main.view_download_button.error
import kotlinx.android.synthetic.main.view_download_button.error_image
import kotlinx.android.synthetic.main.view_download_button.error_text
import kotlinx.android.synthetic.main.view_download_button.progress

class DownloadButton : ViewStateTransitionAnimator, LayoutContainer {

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
        }

    private val stateViews: Map<DownloadState, View> = mapOf(
        DownloadState.AVAILABLE to button,
        DownloadState.IN_PROGRESS to progress,
        DownloadState.COMPLETED to completed,
        DownloadState.ERROR to error
    )

    private var currentState: DownloadState? = null
    private var textAvailable: String? = null
    private var textCompleted: String? = null
    private var textError: String? = null
    private var colorCompleted: Int? = null
    private var colorError: Int? = null

    override val containerView: View?
        get() = this

    private fun init(attrs: AttributeSet?) {
        val typedArray = context?.obtainStyledAttributes(attrs, R.styleable.DownloadButton)

        textAvailable = typedArray?.getString(R.styleable.DownloadButton_text_available)
        textCompleted = typedArray?.getString(R.styleable.DownloadButton_text_completed)
        textError = typedArray?.getString(R.styleable.DownloadButton_text_error)

        colorCompleted = typedArray?.getInt(R.styleable.DownloadButton_color_completed, 0)
        colorError = typedArray?.getInt(R.styleable.DownloadButton_color_error, 0)

        typedArray?.recycle()

        textAvailable?.let { button.text = it }
        textCompleted?.let { completed.text = it }
        textError?.let { error_text.text = it }

        colorCompleted?.let { completed.setTextColor(it) }
        colorError?.let { error_text.setTextColor(it) }
        colorError?.let { error_image.setColorFilter(it, PorterDuff.Mode.SRC_IN) }
    }

    fun setState(state: DownloadState) {
        when (currentState) {
            DownloadState.AVAILABLE -> {
                // Possible transitions:
                //  AVAILABLE -> IN_PROGRESS

            }
            DownloadState.IN_PROGRESS -> {
                // Possible transitions:
                //  IN_PROGRESS -> COMPLETED
                //  IN_PROGRESS -> ERROR

            }
            DownloadState.ERROR -> {
                // Possible transitions:
                //  ERROR -> IN_PROGRESS
            }
            else -> {
                stateViews.values.forEach { it.gone() }
                stateViews[state]?.show()
                currentState = state
            }
        }
    }
}
