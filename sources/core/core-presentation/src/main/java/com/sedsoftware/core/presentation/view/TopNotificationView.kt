package com.sedsoftware.core.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity.CENTER_VERTICAL
import android.view.Gravity.TOP
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.sedsoftware.core.presentation.LayoutHelper
import com.sedsoftware.core.presentation.R
import com.sedsoftware.core.presentation.extension.color
import com.sedsoftware.core.presentation.extension.textColor
import com.sedsoftware.core.presentation.extension.toDp

@SuppressLint("ViewConstructor")
class TopNotificationView(context: Context, coordinator: CoordinatorLayout) : FrameLayout(context) {

    private val textView: TextView = TextView(context)

    init {

        setBackgroundColor(context.color(R.color.colorNotificationSemiTransparent))

        textView.textColor = R.color.colorNotificationTextTop

        addView(
            textView, LayoutHelper.createFrame(
                width = MATCH_PARENT,
                height = WRAP_CONTENT,
                gravity = CENTER_VERTICAL,
                leftMargin = MARGIN_HORIZONTAL.toDp(),
                topMargin = MARGIN_VERTICAL.toDp(),
                rightMargin = MARGIN_HORIZONTAL.toDp(),
                bottomMargin = MARGIN_VERTICAL.toDp()
            )
        )

        coordinator.addView(this, LayoutHelper.createFrame(MATCH_PARENT, WRAP_CONTENT, TOP))
    }

    fun setText(resId: Int) {
        textView.setText(resId)
    }

    fun show() {}

    fun hide() {}

    private companion object {
        const val MARGIN_HORIZONTAL = 16
        const val MARGIN_VERTICAL = 12
    }
}
