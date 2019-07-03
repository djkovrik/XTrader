package com.sedsoftware.core.presentation.view

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.sedsoftware.core.presentation.R
import com.sedsoftware.core.presentation.extension.hide
import com.sedsoftware.core.presentation.extension.show
import com.sedsoftware.core.presentation.params.DownloadState
import com.sedsoftware.core.presentation.params.DownloadState.AVAILABLE
import com.sedsoftware.core.presentation.params.DownloadState.COMPLETED
import com.sedsoftware.core.presentation.params.DownloadState.ERROR
import com.sedsoftware.core.presentation.params.DownloadState.IN_PROGRESS
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_download_button.*

class DownloadButton : FrameLayout, LayoutContainer {

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


    private val views: Map<DownloadState, View> = mapOf(
        AVAILABLE to button,
        IN_PROGRESS to progress,
        COMPLETED to completed,
        ERROR to error
    )

    private var currentState: DownloadState? = null
    private var textAvailable: String? = null
    private var textInProgress: String? = null
    private var textCompleted: String? = null
    private var textError: String? = null
    private var colorAvailable: Int? = null
    private var colorInProgress: Int? = null
    private var colorCompleted: Int? = null
    private var colorError: Int? = null

    override val containerView: View? = this

    private fun init(attrs: AttributeSet?) {
        val typedArray = context?.obtainStyledAttributes(attrs, R.styleable.DownloadButton)

        textAvailable = typedArray?.getString(R.styleable.DownloadButton_text_available)
        textInProgress = typedArray?.getString(R.styleable.DownloadButton_text_in_progress)
        textCompleted = typedArray?.getString(R.styleable.DownloadButton_text_completed)
        textError = typedArray?.getString(R.styleable.DownloadButton_text_error)

        colorAvailable = typedArray?.getInt(R.styleable.DownloadButton_color_available, 0)
        colorInProgress = typedArray?.getInt(R.styleable.DownloadButton_color_in_progress, 0)
        colorCompleted = typedArray?.getInt(R.styleable.DownloadButton_color_completed, 0)
        colorError = typedArray?.getInt(R.styleable.DownloadButton_color_error, 0)

        typedArray?.recycle()

        textAvailable?.let { button.text = it }
        textInProgress?.let { progress.text = it }
        textCompleted?.let { completed.text = it }
        textError?.let { error_text.text = it }

        colorAvailable?.let { button.setTextColor(it) }
        colorInProgress?.let { progress.setTextColor(it) }
        colorCompleted?.let { completed.setTextColor(it) }
        colorError?.let { error_text.setTextColor(it) }
        colorError?.let { error_image.setColorFilter(it, PorterDuff.Mode.SRC_IN) }
    }

    fun setState(newState: DownloadState) {
        currentState?.let { views[it]?.hide() }
        views[newState]?.show()
        currentState = newState
    }
}
