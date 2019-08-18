package com.sedsoftware.core.presentation.extension

import android.animation.Animator
import android.animation.Animator.AnimatorListener

fun Animator.addStartAction(action: () -> Unit): Animator =
    this.apply {
        addListener(object : AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {

            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {
                action.invoke()
            }
        })
    }

fun Animator.addEndAction(action: () -> Unit): Animator =
    this.apply {
        addListener(object : AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                action.invoke()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
    }

fun Animator.addStartEndActions(startWith: () -> Unit, endWith: () -> Unit): Animator =
    this.apply {
        addListener(object : AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                endWith.invoke()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
                startWith.invoke()
            }
        })
    }
