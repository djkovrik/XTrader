package com.sedsoftware.core.presentation

import android.widget.FrameLayout
import android.widget.LinearLayout
import com.sedsoftware.core.presentation.extension.toDp


class LayoutHelper {

    companion object {
        fun createFrame(width: Int, height: Int, gravity: Int, leftMargin: Int, topMargin: Int, rightMargin: Int, bottomMargin: Int): FrameLayout.LayoutParams {
            val layoutParams = FrameLayout.LayoutParams(getSize(width), getSize(height), gravity)
            layoutParams.setMargins(leftMargin.toDp(), topMargin.toDp(), rightMargin.toDp(), bottomMargin.toDp())
            return layoutParams
        }

        fun createFrame(width: Int, height: Int, gravity: Int): FrameLayout.LayoutParams {
            return FrameLayout.LayoutParams(getSize(width), getSize(height), gravity)
        }

        fun createFrame(width: Int, height: Int): FrameLayout.LayoutParams {
            return FrameLayout.LayoutParams(getSize(width), getSize(height))
        }

        fun createLinear(width: Int, height: Int, weight: Float, gravity: Int): LinearLayout.LayoutParams {
            val layoutParams = LinearLayout.LayoutParams(getSize(width), getSize(height), weight)
            layoutParams.gravity = gravity
            return layoutParams
        }

        fun createLinear(width: Int, height: Int, gravity: Int): LinearLayout.LayoutParams {
            val layoutParams = LinearLayout.LayoutParams(getSize(width), getSize(height))
            layoutParams.gravity = gravity
            return layoutParams
        }

        private fun getSize(size: Int): Int {
            return if (size < 0) size else size.toDp()
        }
    }
}
