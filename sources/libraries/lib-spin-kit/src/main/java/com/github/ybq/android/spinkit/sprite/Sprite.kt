package com.github.ybq.android.spinkit.sprite

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Matrix
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.util.Property

import com.github.ybq.android.spinkit.animation.AnimationUtils
import com.github.ybq.android.spinkit.animation.FloatProperty
import com.github.ybq.android.spinkit.animation.IntProperty

/**
 * Created by ybq.
 */
abstract class Sprite : Drawable(), ValueAnimator.AnimatorUpdateListener, Animatable, Drawable.Callback {

    var scale = 1f
        set(value) {
            field = value
            scaleX = value
            scaleY = value
        }

    var scaleX = 1f
    var scaleY = 1f
    var rotateX: Int = 0
    var rotateY: Int = 0
    var translateX: Int = 0
    var translateY: Int = 0
    var rotate: Int = 0

    var translateXPercentage: Float = 0.toFloat()
    var translateYPercentage: Float = 0.toFloat()

    var drawBonds: Rect = ZERO_BOUNDS_RECT

    var animationDelay: Int = 0

    private var pivotX: Float = 0.toFloat()
    private var pivotY: Float = 0.toFloat()

    private var animator: ValueAnimator? = null
    private val mCamera: Camera = Camera()
    private val mMatrix: Matrix = Matrix()

    private var innerAlpha: Int = 255

    abstract fun onCreateAnimation(): ValueAnimator?

    protected abstract fun drawSelf(canvas: Canvas)

    abstract var color: Int

    override fun getAlpha(): Int =
        innerAlpha

    override fun setAlpha(value: Int) {
        innerAlpha = alpha
    }

    @SuppressLint("WrongConstant")
    override fun getOpacity(): Int = PixelFormat.RGBA_8888

    override fun setColorFilter(colorFilter: ColorFilter?) {

    }

    override fun start() {
        if (AnimationUtils.isStarted(animator)) {
            return
        }

        animator = obtainAnimation()
        if (animator == null) {
            return
        }

        AnimationUtils.start(animator)
        invalidateSelf()
    }

    override fun stop() {
        if (AnimationUtils.isStarted(animator)) {
            animator?.removeAllUpdateListeners()
            animator?.end()
            reset()
        }
    }

    override fun isRunning(): Boolean {
        return AnimationUtils.isRunning(animator)
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        setDrawBounds(bounds)
    }

    override fun invalidateDrawable(who: Drawable) {
        invalidateSelf()
    }

    override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {

    }

    override fun unscheduleDrawable(who: Drawable, what: Runnable) {

    }

    override fun onAnimationUpdate(animation: ValueAnimator) {
        val callback = callback
        callback?.invalidateDrawable(this)
    }

    override fun draw(canvas: Canvas) {
        var tx = translateX
        tx = if (tx == 0) (bounds.width() * translateXPercentage).toInt() else tx
        var ty = translateY
        ty = if (ty == 0) (bounds.height() * translateYPercentage).toInt() else ty
        canvas.translate(tx.toFloat(), ty.toFloat())
        canvas.scale(scaleX, scaleY, pivotX, pivotY)
        canvas.rotate(rotate.toFloat(), pivotX, pivotY)

        if (rotateX != 0 || rotateY != 0) {
            mCamera.save()
            mCamera.rotateX(rotateX.toFloat())
            mCamera.rotateY(rotateY.toFloat())
            mCamera.getMatrix(mMatrix)
            mMatrix.preTranslate(-pivotX, -pivotY)
            mMatrix.postTranslate(pivotX, pivotY)
            mCamera.restore()
            canvas.concat(mMatrix)
        }
        drawSelf(canvas)
    }

    fun clipSquare(rect: Rect): Rect {
        val w = rect.width()
        val h = rect.height()
        val min = Math.min(w, h)
        val cx = rect.centerX()
        val cy = rect.centerY()
        val r = min / 2
        return Rect(
            cx - r,
            cy - r,
            cx + r,
            cy + r
        )
    }

    fun setDrawBounds(left: Int, top: Int, right: Int, bottom: Int) {
        this.drawBonds = Rect(left, top, right, bottom)
        pivotX = drawBonds.centerX().toFloat()
        pivotY = drawBonds.centerY().toFloat()
    }

    private fun setDrawBounds(drawBounds: Rect) {
        setDrawBounds(drawBounds.left, drawBounds.top, drawBounds.right, drawBounds.bottom)
    }

    private fun obtainAnimation(): ValueAnimator? {
        if (animator == null) {
            animator = onCreateAnimation()
        }
        if (animator != null) {
            animator!!.addUpdateListener(this)
            animator!!.startDelay = animationDelay.toLong()
        }
        return animator
    }

    private fun reset() {
        scale = 1f
        rotateX = 0
        rotateY = 0
        translateX = 0
        translateY = 0
        rotate = 0
        translateXPercentage = 0f
        translateYPercentage = 0f
    }

    companion object {

        private val ZERO_BOUNDS_RECT = Rect()

        val ROTATE_X: Property<Sprite, Int> = object : IntProperty<Sprite>("rotateX") {
            override fun setValue(target: Sprite, value: Int) {
                target.rotateX = value
            }

            override fun get(target: Sprite): Int =
                target.rotateX
        }

        val ROTATE: Property<Sprite, Int> = object : IntProperty<Sprite>("rotate") {
            override fun setValue(target: Sprite, value: Int) {
                target.rotate = value
            }

            override fun get(target: Sprite): Int =
                target.rotate
        }

        val ROTATE_Y: Property<Sprite, Int> = object : IntProperty<Sprite>("rotateY") {
            override fun setValue(target: Sprite, value: Int) {
                target.rotateY = value
            }

            override fun get(target: Sprite): Int =
                target.rotateY
        }

        val TRANSLATE_X: Property<Sprite, Int> = object : IntProperty<Sprite>("translateX") {
            override fun setValue(target: Sprite, value: Int) {
                target.translateX = value
            }

            override fun get(target: Sprite): Int =
                target.translateX
        }

        val TRANSLATE_Y: Property<Sprite, Int> = object : IntProperty<Sprite>("translateY") {
            override fun setValue(target: Sprite, value: Int) {
                target.translateY = value
            }

            override fun get(target: Sprite): Int =
                target.translateY
        }

        val TRANSLATE_X_PERCENTAGE: Property<Sprite, Float> = object : FloatProperty<Sprite>("translateXPercentage") {
            override fun setValue(target: Sprite, value: Float) {
                target.translateXPercentage = value
            }

            override fun get(target: Sprite): Float =
                target.translateXPercentage
        }

        val TRANSLATE_Y_PERCENTAGE: Property<Sprite, Float> = object : FloatProperty<Sprite>("translateYPercentage") {
            override fun setValue(target: Sprite, value: Float) {
                target.translateYPercentage = value
            }

            override fun get(target: Sprite): Float =
                target.translateYPercentage
        }

        val SCALE_X: Property<Sprite, Float> = object : FloatProperty<Sprite>("scaleX") {
            override fun setValue(target: Sprite, value: Float) {
                target.scaleX = value
            }

            override fun get(target: Sprite): Float =
                target.scaleX
        }

        val SCALE_Y: Property<Sprite, Float> = object : FloatProperty<Sprite>("scaleY") {
            override fun setValue(target: Sprite, value: Float) {
                target.scaleY = value
            }

            override fun get(target: Sprite): Float =
                target.scaleY
        }

        val SCALE: Property<Sprite, Float> = object : FloatProperty<Sprite>("scale") {
            override fun setValue(target: Sprite, value: Float) {
                target.scale = value
            }

            override fun get(target: Sprite): Float =
                target.scale
        }

        val ALPHA: Property<Sprite, Int> = object : IntProperty<Sprite>("alpha") {
            override fun setValue(target: Sprite, value: Int) {
                target.alpha = value
            }

            override fun get(target: Sprite): Int =
                target.alpha
        }
    }
}
