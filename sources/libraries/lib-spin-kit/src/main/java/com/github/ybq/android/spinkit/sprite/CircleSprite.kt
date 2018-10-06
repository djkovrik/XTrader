package com.github.ybq.android.spinkit.sprite

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Paint

/**
 * Created by ybq.
 */
open class CircleSprite : ShapeSprite() {

    override fun onCreateAnimation(): ValueAnimator? = null

    override fun drawShape(canvas: Canvas, paint: Paint) {
        val radius = Math.min(drawBonds.width(), drawBonds.height()) / 2
        canvas.drawCircle(
            drawBonds.centerX().toFloat(),
            drawBonds.centerY().toFloat(),
            radius.toFloat(), paint
        )
    }
}
