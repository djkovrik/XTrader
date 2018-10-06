package com.github.ybq.android.spinkit.sprite

import android.graphics.Canvas
import android.graphics.Rect

/**
 * Created by ybq.
 */
abstract class CircleLayoutContainer : SpriteContainer() {

    override fun drawChild(canvas: Canvas) {
        for (i in 0 until childCount) {
            val sprite = getChildAt(i)
            val count = canvas.save()
            canvas.rotate(
                (i * 360 / childCount).toFloat(),
                bounds.centerX().toFloat(),
                bounds.centerY().toFloat()
            )
            sprite?.draw(canvas)
            canvas.restoreToCount(count)
        }
    }

    override fun onBoundsChange(bounds: Rect) {
        var innerBounds = bounds
        super.onBoundsChange(innerBounds)
        innerBounds = clipSquare(innerBounds)
        val radius = (innerBounds.width() * Math.PI / 3.6 / childCount.toDouble()).toInt()
        val left = innerBounds.centerX() - radius
        val right = innerBounds.centerX() + radius
        for (i in 0 until childCount) {
            val sprite = getChildAt(i)
            sprite!!.setDrawBounds(left, innerBounds.top, right, innerBounds.top + radius * 2)
        }
    }
}
