package com.github.ybq.android.spinkit.sprite

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint

/**
 * Created by ybq.
 */
abstract class ShapeSprite : Sprite() {

    private val mPaint: Paint
    private var useColor: Int = 0

    abstract fun drawShape(canvas: Canvas, paint: Paint)

    final override var color: Int = 0
        set(value) {
            field = value
            updateUseColor()
        }

    init {
        this.color = Color.WHITE
        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.color = useColor
    }

    override fun setAlpha(value: Int) {
        super.setAlpha(value)
        updateUseColor()
    }

    private fun updateUseColor() {
        var alpha = alpha
        alpha += alpha shr 7
        val baseAlpha = color.ushr(24)
        val useAlpha = baseAlpha * alpha shr 8
        useColor = (color shl 8).ushr(8) or (useAlpha shl 24)
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.colorFilter = colorFilter
    }

    override fun drawSelf(canvas: Canvas) {
        mPaint.color = useColor
        drawShape(canvas, mPaint)
    }
}
