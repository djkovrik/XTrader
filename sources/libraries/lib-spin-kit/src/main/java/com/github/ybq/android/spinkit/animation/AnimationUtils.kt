package com.github.ybq.android.spinkit.animation

import android.animation.Animator
import android.animation.ValueAnimator

import com.github.ybq.android.spinkit.sprite.Sprite

/**
 * Created by ybq.
 */
object AnimationUtils {

    fun start(animator: Animator?) {
        if (animator != null && !animator.isStarted) {
            animator.start()
        }
    }

    fun stop(animator: Animator?) {
        if (animator != null && !animator.isRunning) {
            animator.end()
        }
    }

    fun start(vararg sprites: Sprite?) {
        for (sprite in sprites) {
            sprite?.start()
        }
    }

    fun stop(vararg sprites: Sprite?) {
        for (sprite in sprites) {
            sprite?.stop()
        }
    }

    fun isRunning(vararg sprites: Sprite?): Boolean {
        for (sprite in sprites) {
            if (sprite?.isRunning == true) {
                return true
            }
        }
        return false
    }

    fun isRunning(animator: ValueAnimator?): Boolean =
        animator != null && animator.isRunning

    fun isStarted(animator: ValueAnimator?): Boolean =
        animator != null && animator.isStarted
}
