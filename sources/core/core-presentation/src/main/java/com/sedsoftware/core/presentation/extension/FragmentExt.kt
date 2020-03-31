package com.sedsoftware.core.presentation.extension

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.sedsoftware.core.utils.extension.orZero

fun Fragment.string(@StringRes resId: Int, vararg formatArgs: Any? = arrayOfNulls(0)): String =
    context?.getString(resId, formatArgs).orEmpty()

fun Fragment.color(@ColorRes colorId: Int): Int =
    context?.let { ContextCompat.getColor(it, colorId) }.orZero()
