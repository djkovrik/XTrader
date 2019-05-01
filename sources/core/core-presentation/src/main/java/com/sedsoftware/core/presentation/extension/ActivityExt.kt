package com.sedsoftware.core.presentation.extension

import android.app.Activity
import androidx.annotation.ColorRes

fun Activity.setBackgroundColor(@ColorRes colorId: Int) {
    window?.decorView?.setBackgroundColor(color(colorId))
}
