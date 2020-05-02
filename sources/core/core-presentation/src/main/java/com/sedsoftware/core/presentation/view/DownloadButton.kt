package com.sedsoftware.core.presentation.view

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.sedsoftware.core.presentation.R
import com.sedsoftware.core.presentation.databinding.ViewDownloadButtonBinding

class DownloadButton : FrameLayout {

    enum class State { AVAILABLE, IN_PROGRESS, COMPLETED, ERROR }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    var clickListener: () -> Unit = {}
        set(value) {
            field = value
            binding.button.setOnClickListener { field.invoke() }
            binding.errorText.setOnClickListener { field.invoke() }
            binding.errorImage.setOnClickListener { field.invoke() }
        }

    private val binding: ViewDownloadButtonBinding get() = _binding!!
    private var _binding: ViewDownloadButtonBinding? = null

    private lateinit var views: Map<State, View>

    private var currentState: State? = null
    private var textAvailable: String? = null
    private var textInProgress: String? = null
    private var textCompleted: String? = null
    private var textError: String? = null
    private var colorAvailable: Int? = null
    private var colorInProgress: Int? = null
    private var colorCompleted: Int? = null
    private var colorError: Int? = null

    private fun init(attrs: AttributeSet? = null) {

        _binding = ViewDownloadButtonBinding.inflate(LayoutInflater.from(context), this, true)

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

        with(binding) {
            textAvailable?.let { button.text = it }
            textInProgress?.let { progress.text = it }
            textCompleted?.let { completed.text = it }
            textError?.let { errorText.text = it }

            colorAvailable?.let { button.setTextColor(it) }
            colorInProgress?.let { progress.setTextColor(it) }
            colorCompleted?.let { completed.setTextColor(it) }
            colorError?.let { errorText.setTextColor(it) }
            colorError?.let { errorImage.setColorFilter(it, PorterDuff.Mode.SRC_IN) }
        }

        views = mapOf(
            State.AVAILABLE to binding.button,
            State.IN_PROGRESS to binding.progress,
            State.COMPLETED to binding.completed,
            State.ERROR to binding.error
        )
    }

    fun setState(newState: State) {
        currentState?.let { views[it]?.visibility = View.INVISIBLE }
        views[newState]?.visibility = View.VISIBLE
        currentState = newState
    }
}
