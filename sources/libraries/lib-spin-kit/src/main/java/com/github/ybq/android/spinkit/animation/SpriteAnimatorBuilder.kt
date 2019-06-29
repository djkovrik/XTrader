package com.github.ybq.android.spinkit.animation


import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.util.Log
import android.util.Property
import android.view.animation.Animation
import android.view.animation.Interpolator
import com.github.ybq.android.spinkit.animation.interpolator.KeyFrameInterpolator
import com.github.ybq.android.spinkit.sprite.Sprite
import java.util.*

/**
 * Created by ybq.
 */
@Suppress("MagicNumber", "SpreadOperator")
class SpriteAnimatorBuilder(private val sprite: Sprite) {

    companion object {
        private const val TAG = "SpriteAnimatorBuilder"
    }

    private var interpolator: Interpolator? = null
    private var repeatCount = Animation.INFINITE
    private var duration: Long = 2000
    private var startFrame = 0
    private val fds = HashMap<String, FrameData<*>>()


    internal open inner class FrameData<T>(
        var fractions: FloatArray,
        var property: Property<*, *>,
        var values: Array<T>
    )

    internal inner class IntFrameData(fractions: FloatArray, property: Property<*, *>, values: Array<Int>) :
        FrameData<Int>(fractions, property, values)

    internal inner class FloatFrameData(fractions: FloatArray, property: Property<*, *>, values: Array<Float>) :
        FrameData<Float>(fractions, property, values)

    fun scale(fractions: FloatArray, vararg scale: Float): SpriteAnimatorBuilder {
        holder(fractions, Sprite.SCALE, scale.toTypedArray())
        return this
    }

    fun alpha(fractions: FloatArray, vararg alpha: Int): SpriteAnimatorBuilder {
        holder(fractions, Sprite.ALPHA, alpha.toTypedArray())
        return this
    }

    fun scaleX(fractions: FloatArray, vararg scaleX: Float): SpriteAnimatorBuilder {
        holder(fractions, Sprite.SCALE, scaleX.toTypedArray())
        return this
    }

    fun scaleY(fractions: FloatArray, vararg scaleY: Float): SpriteAnimatorBuilder {
        holder(fractions, Sprite.SCALE_Y, scaleY.toTypedArray())
        return this
    }

    fun rotateX(fractions: FloatArray, vararg rotateX: Int): SpriteAnimatorBuilder {
        holder(fractions, Sprite.ROTATE_X, rotateX.toTypedArray())
        return this
    }

    fun rotateY(fractions: FloatArray, vararg rotateY: Int): SpriteAnimatorBuilder {
        holder(fractions, Sprite.ROTATE_Y, rotateY.toTypedArray())
        return this
    }

    fun translateX(fractions: FloatArray, vararg translateX: Int): SpriteAnimatorBuilder {
        holder(fractions, Sprite.TRANSLATE_X, translateX.toTypedArray())
        return this
    }


    fun translateY(fractions: FloatArray, vararg translateY: Int): SpriteAnimatorBuilder {
        holder(fractions, Sprite.TRANSLATE_Y, translateY.toTypedArray())
        return this
    }


    fun rotate(fractions: FloatArray, vararg rotate: Int): SpriteAnimatorBuilder {
        holder(fractions, Sprite.ROTATE, rotate.toTypedArray())
        return this
    }

    fun translateXPercentage(fractions: FloatArray, vararg translateXPercentage: Float): SpriteAnimatorBuilder {
        holder(fractions, Sprite.TRANSLATE_X_PERCENTAGE, translateXPercentage.toTypedArray())
        return this
    }

    fun translateYPercentage(fractions: FloatArray, vararg translateYPercentage: Float): SpriteAnimatorBuilder {
        holder(fractions, Sprite.TRANSLATE_Y_PERCENTAGE, translateYPercentage.toTypedArray())
        return this
    }

    private fun holder(fractions: FloatArray, property: Property<*, *>, values: Array<Float>) {
        ensurePair(fractions.size, values.size)
        fds[property.name] = FloatFrameData(fractions, property, values)
    }


    private fun holder(fractions: FloatArray, property: Property<*, *>, values: Array<Int>) {
        ensurePair(fractions.size, values.size)
        fds[property.name] = IntFrameData(fractions, property, values)
    }

    private fun ensurePair(fractionsLength: Int, valuesLength: Int) {
        if (fractionsLength != valuesLength) {
            throw IllegalStateException(
                String.format(
                    Locale.getDefault(),
                    "The fractions.length must equal values.length, " + "fraction.length[%d], values.length[%d]",
                    fractionsLength,
                    valuesLength
                )
            )
        }
    }


    fun interpolator(interpolator: Interpolator): SpriteAnimatorBuilder {
        this.interpolator = interpolator
        return this
    }

    fun easeInOut(vararg fractions: Float): SpriteAnimatorBuilder {
        interpolator(
            KeyFrameInterpolator.easeInOut(
                *fractions
            )
        )
        return this
    }


    fun duration(duration: Long): SpriteAnimatorBuilder {
        this.duration = duration
        return this
    }

    fun repeatCount(repeatCount: Int): SpriteAnimatorBuilder {
        this.repeatCount = repeatCount
        return this
    }

    fun startFrame(startFrame: Int): SpriteAnimatorBuilder {
        var innerStartFrame = startFrame
        if (innerStartFrame < 0) {
            Log.w(TAG, "startFrame should always be non-negative")
            innerStartFrame = 0
        }
        this.startFrame = innerStartFrame
        return this
    }

    fun build(): ObjectAnimator {

        val holders = arrayOfNulls<PropertyValuesHolder>(fds.size)
        var i = 0
        for ((_, data) in fds) {
            val keyframes = arrayOfNulls<Keyframe>(data.fractions.size)
            val fractions = data.fractions
            val startF = fractions[startFrame]
            for (j in startFrame until startFrame + data.values.size) {
                val key = j - startFrame
                val vk = j % data.values.size
                var fraction = fractions[vk] - startF
                if (fraction < 0) {
                    fraction = fractions[fractions.size - 1] + fraction
                }
                if (data is IntFrameData) {
                    keyframes[key] = Keyframe.ofInt(fraction, data.values[vk])
                } else if (data is FloatFrameData) {
                    keyframes[key] = Keyframe.ofFloat(fraction, data.values[vk])
                } else {
                    keyframes[key] = Keyframe.ofObject(fraction, data.values[vk])
                }
            }
            holders[i] = PropertyValuesHolder.ofKeyframe(data.property, *keyframes)
            i++
        }

        val animator = ObjectAnimator.ofPropertyValuesHolder(
            sprite,
            *holders
        )
        animator.duration = duration
        animator.repeatCount = repeatCount
        animator.interpolator = interpolator
        return animator
    }
}
