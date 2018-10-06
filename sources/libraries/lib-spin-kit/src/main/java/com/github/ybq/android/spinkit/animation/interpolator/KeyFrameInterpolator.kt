package com.github.ybq.android.spinkit.animation.interpolator

import android.animation.TimeInterpolator
import android.view.animation.Interpolator

/**
 * Created by ybq.
 */
class KeyFrameInterpolator(
    private val interpolator: TimeInterpolator,
    private vararg var fractions: Float
) : Interpolator {

    companion object {

        fun easeInOut(vararg newFractions: Float): KeyFrameInterpolator {
            val interpolator = KeyFrameInterpolator(Ease.inOut())
            interpolator.fractions = newFractions
            return interpolator
        }
    }

    @Synchronized
    override fun getInterpolation(input: Float): Float {
        var localInput = input
        if (fractions.size > 1) {
            for (i in 0 until fractions.size - 1) {
                val start = fractions[i]
                val end = fractions[i + 1]
                val duration = end - start
                if (localInput in start..end) {
                    localInput = (localInput - start) / duration
                    return start + interpolator.getInterpolation(localInput) * duration
                }
            }
        }
        return interpolator.getInterpolation(localInput)
    }
}
