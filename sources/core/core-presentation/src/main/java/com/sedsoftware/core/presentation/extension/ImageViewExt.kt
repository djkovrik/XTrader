package com.sedsoftware.core.presentation.extension

import android.widget.ImageView
import com.sedsoftware.core.presentation.R

fun ImageView.dim(active: Boolean) {
    if (active) {
        drawable.mutate().setTint(context.color(R.color.colorImageDim))
    } else {
        drawable.mutate().setTintList(null)
    }
}
