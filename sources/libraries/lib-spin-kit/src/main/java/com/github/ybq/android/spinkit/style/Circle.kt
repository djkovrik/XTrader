package com.github.ybq.android.spinkit.style

import android.animation.ValueAnimator
import android.os.Build
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder
import com.github.ybq.android.spinkit.sprite.CircleLayoutContainer
import com.github.ybq.android.spinkit.sprite.CircleSprite
import com.github.ybq.android.spinkit.sprite.Sprite

/**
 * Created by ybq.
 */
class Circle : CircleLayoutContainer() {

    override fun onCreateChild(): Array<out Sprite?> {
        val dots = arrayOfNulls<Dot>(12)
        for (i in dots.indices) {
            dots[i] = Dot()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dots[i]?.animationDelay = 1200 / 12 * i
            } else {
                dots[i]?.animationDelay = 1200 / 12 * i + -1200
            }
        }
        return dots
    }

    private inner class Dot internal constructor() : CircleSprite() {

        init {
            scale = 0f
        }

        override fun onCreateAnimation(): ValueAnimator? {
            val fractions = floatArrayOf(0f, 0.5f, 1f)
            return SpriteAnimatorBuilder(this).scale(fractions, 0f, 1f, 0f).duration(1200).easeInOut(*fractions)
                .build()
        }
    }
}
