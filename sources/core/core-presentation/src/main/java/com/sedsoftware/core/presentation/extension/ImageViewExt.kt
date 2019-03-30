package com.sedsoftware.core.presentation.extension

import android.graphics.PorterDuff
import android.widget.ImageView
import com.sedsoftware.core.presentation.R

fun ImageView.dim(active: Boolean) {
    if (active) {
        setColorFilter(R.color.colorImageDim, PorterDuff.Mode.SRC_IN)
    } else {
        clearColorFilter()
    }
}
