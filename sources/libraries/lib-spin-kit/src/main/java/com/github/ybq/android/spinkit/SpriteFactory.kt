package com.github.ybq.android.spinkit

import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Circle
import com.github.ybq.android.spinkit.style.ThreeBounce

/**
 * Created by ybq.
 */
object SpriteFactory {

    fun create(style: Style): Sprite? =
        when (style) {
            Style.THREE_BOUNCE -> ThreeBounce()
            Style.CIRCLE -> Circle()
        }
}
