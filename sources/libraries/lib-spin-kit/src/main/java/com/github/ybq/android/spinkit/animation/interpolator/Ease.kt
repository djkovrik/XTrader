package com.github.ybq.android.spinkit.animation.interpolator

import android.view.animation.Interpolator

/**
 * Created by ybq.
 */
@Suppress("MagicNumber")
object Ease {

    fun inOut(): Interpolator =
        PathInterpolatorCompat.create(0.42f, 0f, 0.58f, 1f)
}
