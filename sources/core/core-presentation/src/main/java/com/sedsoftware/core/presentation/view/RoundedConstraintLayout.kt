package com.sedsoftware.core.presentation.view

import android.content.Context
import android.graphics.Outline
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.sedsoftware.core.presentation.R
import com.sedsoftware.core.presentation.extension.roundTopCorners

class RoundedConstraintLayout : ConstraintLayout {

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet? = null) {
        val typedArray = context?.obtainStyledAttributes(attrs, R.styleable.RoundedConstraintLayout)
        val radius =
            typedArray?.getDimension(R.styleable.RoundedConstraintLayout_corner_radius_constraint, 0f) ?: 0f
        typedArray?.recycle()

        roundTopCorners(radius)
    }
}
