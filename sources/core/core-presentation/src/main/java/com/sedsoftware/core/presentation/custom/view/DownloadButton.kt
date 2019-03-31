package com.sedsoftware.core.presentation.custom.view

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ViewAnimator
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.sedsoftware.core.presentation.R
import com.sedsoftware.core.utils.enums.DownloadState
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_download_button.*

class DownloadButton : ViewAnimator, LayoutContainer {

    constructor(context: Context?) : super(context) {
        LayoutInflater.from(context).inflate(R.layout.view_download_button, this, true)
        init(null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_download_button, this, true)
        init(attrs)
    }

    var clickListener: () -> Unit = {}
        set(value) {
            field = value
            button?.setOnClickListener { field.invoke() }
            error_text?.setOnClickListener { field.invoke() }
        }

    private var state: DownloadState = DownloadState.AVAILABLE
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

        refreshDisplayingState()
    }

    fun setState(state: DownloadState) {
        this.state = state
        refreshDisplayingState()
    }

    private fun refreshDisplayingState() {
        when (state) {
            DownloadState.AVAILABLE -> {
                button.isVisible = true
                progress.isGone = true
                completed.isGone = true
                error.isGone = true
            }
            DownloadState.IN_PROGRESS -> {
                button.isGone = true
                progress.isVisible = true
                completed.isGone = true
                error.isGone = true
            }
            DownloadState.COMPLETED -> {
                button.isGone = true
                progress.isGone = true
                completed.isVisible = true
                error.isGone = true
            }
            DownloadState.ERROR -> {
                button.isGone = true
                progress.isGone = true
                completed.isGone = true
                error.isVisible = true
            }
        }
    }
}
