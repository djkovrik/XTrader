package com.sedsoftware.core.presentation.extension

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

fun Context.color(@ColorRes colorId: Int) =
    ContextCompat.getColor(this, colorId)

fun Context.string(@StringRes resId: Int): String =
    resources.getString(resId)
