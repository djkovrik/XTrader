package com.github.ybq.android.spinkit.sprite

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint

/**
 * Created by ybq.
 */
@Suppress("MagicNumber")
abstract class ShapeSprite : Sprite() {

    private val shapePaint: Paint
    private var useColor: Int = 0

    abstract fun drawShape(canvas: Canvas, paint: Paint)

    final override var color: Int = 0
        set(value) {
            field = value
            updateUseColor()
        }

    init {
        this.color = Color.WHITE
        shapePaint = Paint()
        shapePaint.isAntiAlias = true
        shapePaint.color = useColor
    }

    override fun setAlpha(value: Int) {
        super.setAlpha(value)
        updateUseColor()
    }

    private fun updateUseColor() {
        var innerAlpha = alpha
        innerAlpha += innerAlpha shr 7
        val baseAlpha = color.ushr(24)
        val useAlpha = baseAlpha * innerAlpha shr 8
        useColor = (color shl 8).ushr(8) or (useAlpha shl 24)
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        shapePaint.colorFilter = colorFilter
    }

    override fun drawSelf(canvas: Canvas) {
        shapePaint.color = useColor
        drawShape(canvas, shapePaint)
    }
}
