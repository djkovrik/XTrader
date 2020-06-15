package com.sedsoftware.core.presentation.extension

import android.graphics.Outline
import android.os.Build
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.RequiresApi

fun View.centerX() = this.x + this.width / 2

fun View.centerY() = this.y + this.height / 2

fun View.roundTopCorners(radius: Float) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        outlineProvider = object : ViewOutlineProvider() {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(0, 0, view.width, (view.height + radius).toInt(), radius)
            }
        }

        clipToOutline = true
    }
}
