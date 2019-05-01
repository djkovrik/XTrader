package com.sedsoftware.core.presentation.extension

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.ViewPropertyAnimator

fun ViewPropertyAnimator.addStartAction(action: () -> Unit): ViewPropertyAnimator =
    this.apply {
        setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                action.invoke()
            }
        })
    }

fun ViewPropertyAnimator.addEndAction(action: () -> Unit): ViewPropertyAnimator =
    this.apply {
        setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                action.invoke()
            }
        })
    }

fun ViewPropertyAnimator.addStartEndActions(startWith: () -> Unit, endWith: () -> Unit): ViewPropertyAnimator =
    this.apply {
        setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                startWith.invoke()
            }

            override fun onAnimationEnd(animation: Animator?) {
                endWith.invoke()
            }
        })
    }