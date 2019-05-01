package com.github.ybq.android.spinkit.style

import android.animation.ValueAnimator
import android.graphics.Rect
import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder
import com.github.ybq.android.spinkit.sprite.CircleSprite
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.sprite.SpriteContainer

/**
 * Created by ybq.
 */
@Suppress("MagicNumber", "SpreadOperator")
class ThreeBounce : SpriteContainer() {

    override fun onCreateChild(): Array<Sprite> =
        arrayOf(Bounce(), Bounce(), Bounce())

    override fun onChildCreated(vararg sprites: Sprite?) {
        super.onChildCreated(*sprites)
        sprites[1]?.animationDelay = 160
        sprites[2]?.animationDelay = 320
    }

    override fun onBoundsChange(bounds: Rect) {
        var innerBounds = bounds
        super.onBoundsChange(innerBounds)
        innerBounds = clipSquare(bounds)
        val radius = innerBounds.width() / 8
        val top = innerBounds.centerY() - radius
        val bottom = innerBounds.centerY() + radius

        for (index in 0 until childCount) {
            val left = innerBounds.width() * index / 3 + innerBounds.left
            getChildAt(index)?.setDrawBounds(
                left, top, left + radius * 2, bottom
            )
        }
    }

    private inner class Bounce internal constructor() : CircleSprite() {

        init {
            scale = 0f
        }

        override fun onCreateAnimation(): ValueAnimator? {
            val fractions = floatArrayOf(0f, 0.4f, 0.8f, 1f)
            return SpriteAnimatorBuilder(this)
                .scale(fractions, 0f, 1f, 0f, 0f)
                .duration(1400)
                .easeInOut(*fractions)
                .build()
        }
    }
}
