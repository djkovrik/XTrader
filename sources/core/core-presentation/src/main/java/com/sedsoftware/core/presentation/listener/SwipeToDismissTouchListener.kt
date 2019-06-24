package com.sedsoftware.core.presentation.listener

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.view.animation.AccelerateInterpolator
import com.sedsoftware.core.presentation.extension.addEndAction

// Based on https://raw.githubusercontent.com/romannurik/Android-SwipeToDismiss
class SwipeToDismissTouchListener(
    private val targetView: View,
    private val callback: DismissCallbacks
) : View.OnTouchListener {

    private val slop: Int
    private val minFlingVelocity: Int
    private val maxFlingVelocity: Int
    private val animationTime: Long
    private var viewWidth = 1

    private var downX: Float = 0f
    private var downY: Float = 0f
    private var translationX: Float = 0f
    private var swiping: Boolean = false
    private var dismissed: Boolean = false
    private var swipingSlop: Int = 0
    private var velocityTracker: VelocityTracker? = null

    interface DismissCallbacks {
        fun onDismiss(view: View)
    }

    init {
        val viewConfiguration = ViewConfiguration.get(targetView.context)
        slop = viewConfiguration.scaledTouchSlop
        minFlingVelocity = viewConfiguration.scaledMinimumFlingVelocity * 16
        maxFlingVelocity = viewConfiguration.scaledMaximumFlingVelocity
        animationTime = targetView.context.resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        motionEvent.offsetLocation(translationX, 0f)

        if (viewWidth < 2) {
            viewWidth = targetView.width
        }

        when (motionEvent.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                dismissed = false
                downX = motionEvent.rawX
                downY = motionEvent.rawY
                velocityTracker = VelocityTracker.obtain()
                velocityTracker?.addMovement(motionEvent)
                return true
            }

            MotionEvent.ACTION_UP -> {
                if (velocityTracker == null) {
                    return false
                }

                val deltaX = motionEvent.rawX - downX
                velocityTracker?.addMovement(motionEvent)
                velocityTracker?.computeCurrentVelocity(1000)
                val velocityX = velocityTracker!!.xVelocity
                val absVelocityX = Math.abs(velocityX)
                val absVelocityY = Math.abs(velocityTracker!!.yVelocity)
                var dismiss = false
                var dismissRight = false
                if (Math.abs(deltaX) > viewWidth / 2 && swiping) {
                    dismiss = true
                    dismissRight = deltaX > 0
                } else if (minFlingVelocity <= absVelocityX && absVelocityX <= maxFlingVelocity
                    && absVelocityY < absVelocityX
                    && absVelocityY < absVelocityX && swiping) {
                    // dismiss only if flinging in the same direction as dragging
                    dismiss = velocityX < 0 == deltaX < 0
                    dismissRight = velocityTracker!!.xVelocity > 0
                }
                if (dismiss) {
                    targetView.animate()
                        .translationX((if (dismissRight) viewWidth else -viewWidth).toFloat())
                        .alpha(0f)
                        .setDuration(animationTime)
                        .setInterpolator(AccelerateInterpolator())
                        .addEndAction { performDismiss() }
                        .start()
                }

                velocityTracker?.recycle()
                velocityTracker = null
                translationX = 0f
                downX = 0f
                downY = 0f
                swiping = false
            }

            MotionEvent.ACTION_CANCEL -> {
                if (velocityTracker == null) {
                    return false
                }

                targetView.animate()
                    .translationX(0f)
                    .alpha(1f)
                    .setDuration(animationTime)
                    .setInterpolator(AccelerateInterpolator())
                    .setListener(null)
                    .start()

                velocityTracker?.recycle()
                velocityTracker = null
                translationX = 0f
                downX = 0f
                downY = 0f
                swiping = false
            }

            MotionEvent.ACTION_MOVE -> {
                if (velocityTracker == null) {
                    return false
                }

                velocityTracker!!.addMovement(motionEvent)
                val deltaX = motionEvent.rawX - downX
                val deltaY = motionEvent.rawY - downY
                if (Math.abs(deltaX) > slop && Math.abs(deltaY) < Math.abs(deltaX) / 2) {
                    swiping = true
                    swipingSlop = if (deltaX > 0) slop else -slop
                    targetView.parent.requestDisallowInterceptTouchEvent(true)

                    val cancelEvent = MotionEvent.obtain(motionEvent)
                    cancelEvent.action = MotionEvent.ACTION_CANCEL or
                            (motionEvent.actionIndex shl MotionEvent.ACTION_POINTER_INDEX_SHIFT)
                    targetView.onTouchEvent(cancelEvent)
                    cancelEvent.recycle()
                }
            }
        }
        return false
    }

    private fun performDismiss() {
        if (dismissed) {
            return
        }
        dismissed = true

        val params = targetView.layoutParams
        val originalHeight = targetView.height

        val animator = ValueAnimator.ofInt(originalHeight, 1).setDuration(animationTime)

        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                callback.onDismiss(targetView)
                targetView.alpha = 1f
                targetView.translationX = 0f
                params.height = originalHeight
                targetView.layoutParams = params
            }
        })

        animator.addUpdateListener { valueAnimator ->
            params.height = valueAnimator.animatedValue as Int
            targetView.layoutParams = params
        }

        animator.start()
    }
}
