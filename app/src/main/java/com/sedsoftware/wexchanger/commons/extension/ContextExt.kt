package com.sedsoftware.wexchanger.commons.extension

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import com.mikepenz.community_material_typeface_library.CommunityMaterial
import com.mikepenz.iconics.IconicsDrawable

fun Context.color(@ColorRes colorId: Int) =
  ContextCompat.getColor(this, colorId)

fun Context.string(@StringRes resId: Int): String =
  resources.getString(resId)

fun Context.iconics(id: CommunityMaterial.Icon): Drawable =
  IconicsDrawable(this).icon(id)
