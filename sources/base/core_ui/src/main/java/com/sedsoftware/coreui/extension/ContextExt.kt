package com.sedsoftware.coreui.extension

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

fun Context.color(@ColorRes colorId: Int) =
    ContextCompat.getColor(this, colorId)

fun Context.string(@StringRes resId: Int): String =
    resources.getString(resId)

fun Context.colorFromAttr(@AttrRes res: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(res, typedValue, true)
    return typedValue.data
}
