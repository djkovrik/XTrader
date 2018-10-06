package com.github.ybq.android.spinkit.sprite

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Rect

import com.github.ybq.android.spinkit.animation.AnimationUtils

/**
 * Created by ybq.
 */
abstract class SpriteContainer : Sprite() {

    private var sprites: Array<out Sprite?>

    abstract fun onCreateChild(): Array<out Sprite?>

    override var color: Int = 0
        set(color) {
            field = color
            for (i in 0 until childCount) {
                getChildAt(i)?.color = color
            }
        }

    val childCount: Int
        get() = sprites.size

    init {
        sprites = this.onCreateChild()
        initCallBack()
        this.onChildCreated(*sprites)
    }

    private fun initCallBack() {
        for (sprite in sprites) {
            sprite?.callback = this
        }
    }

    open fun onChildCreated(vararg sprites: Sprite?) {

    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        drawChild(canvas)
    }

    override fun drawSelf(canvas: Canvas) {}

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        for (sprite in sprites) {
            sprite?.bounds = bounds
        }
    }

    override fun start() {
        super.start()
        AnimationUtils.start(*sprites)
    }

    override fun stop() {
        super.stop()
        AnimationUtils.stop(*sprites)
    }

    override fun isRunning(): Boolean {
        return AnimationUtils.isRunning(*sprites) || super.isRunning()
    }

    override fun onCreateAnimation(): ValueAnimator? = null

    open fun drawChild(canvas: Canvas) {
        for (sprite in sprites) {
            val count = canvas.save()
            sprite?.draw(canvas)
            canvas.restoreToCount(count)
        }
    }

    fun getChildAt(index: Int): Sprite? = sprites[index]
}
