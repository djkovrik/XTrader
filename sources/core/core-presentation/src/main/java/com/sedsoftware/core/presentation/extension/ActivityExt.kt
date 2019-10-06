package com.sedsoftware.core.presentation.extension

import android.app.Activity
import androidx.annotation.ColorRes
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.AppProvider

fun Activity.setBackgroundColor(@ColorRes colorId: Int) {
    window?.decorView?.setBackgroundColor(color(colorId))
}
