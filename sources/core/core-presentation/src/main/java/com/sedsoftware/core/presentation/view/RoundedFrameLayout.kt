package com.sedsoftware.core.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.sedsoftware.core.presentation.R
import com.sedsoftware.core.presentation.extension.roundTopCorners

class RoundedFrameLayout : FrameLayout {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet? = null) {
        val typedArray = context?.obtainStyledAttributes(attrs, R.styleable.RoundedFrameLayout)
        val radius = typedArray?.getDimension(R.styleable.RoundedFrameLayout_corner_radius_frame, 0f) ?: 0f
        typedArray?.recycle()

        roundTopCorners(radius)
    }
}
