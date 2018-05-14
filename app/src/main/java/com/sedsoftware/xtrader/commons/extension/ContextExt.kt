package com.sedsoftware.xtrader.commons.extension

import android.content.Context
import android.support.annotation.AttrRes
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.util.TypedValue

fun Context.color(@ColorRes colorId: Int) =
  ContextCompat.getColor(this, colorId)

fun Context.string(@StringRes resId: Int): String =
  resources.getString(resId)

fun Context.colorFromAttr(@AttrRes resId: Int): Int {
  val typedValue = TypedValue()
  theme.resolveAttribute(resId, typedValue, true)
  return typedValue.data
}
