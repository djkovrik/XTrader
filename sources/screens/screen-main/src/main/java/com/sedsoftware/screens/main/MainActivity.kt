package com.sedsoftware.screens.main

import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.delegate.SnackbarDelegate
import com.sedsoftware.core.di.holder.ActivityToolsHolder
import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.presentation.base.BaseActivity
import com.sedsoftware.core.presentation.extension.addEndAction
import com.sedsoftware.core.presentation.extension.launch
import com.sedsoftware.core.presentation.extension.setBackgroundColor
import com.sedsoftware.core.presentation.extension.viewModel
import com.sedsoftware.core.presentation.listener.SwipeToDismissTouchListener
import com.sedsoftware.core.presentation.listener.SwipeToDismissTouchListener.DismissCallbacks
import com.sedsoftware.screens.main.di.MainActivityComponent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay

class MainActivity : BaseActivity(), ActivityToolsHolder, SnackbarDelegate {

    companion object {
        // Animation
        private const val ANIMATION_DELAY = 100L
        private const val ANIMATION_DURATION = 200L
        private const val DELAY_BEFORE_HIDE = 2000L
    }

    private val mainActivityComponent: MainActivityComponent by lazy {
        val appComponent = (applicationContext as App).getAppComponent()
        MainActivityComponent.Initializer.init(appComponent, this)
    }

    private lateinit var mainActivityViewModel: MainActivityViewModel

    private var topNotificationTranslation = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivityComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setupViews()

        mainActivityViewModel = viewModel(viewModelFactory) {

        }
    }

    private fun setupViews() {
        setBackgroundColor(R.color.colorBackground)

        topNotificationTextView.post {
            topNotificationTranslation = -topNotificationTextView.measuredHeight.toFloat()
            topNotificationTextView.translationY = topNotificationTranslation
        }

        topNotificationTextView.setOnTouchListener(
            SwipeToDismissTouchListener(
                topNotificationTextView,
                object : DismissCallbacks {
                    override fun onDismiss(view: View) {
                        topNotificationTextView.translationY = topNotificationTranslation
                        notificationJob?.cancelChildren()
                        notificationQueue.clear()
                    }
                }
            ))
    }


    override fun onPause() {
        notificationJob?.cancel()
        super.onPause()
    }

    override fun getActivityToolsProvider(): MainActivityToolsProvider =
        mainActivityComponent

    override fun notifyOnTop(message: String) {
        if (!isNotificationVisible) {
            topNotificationTextView.text = message
            translateViewAnimated(topNotificationTextView, 0f) {
                isNotificationVisible = true
                hideNotificationDelayed()
            }
        } else {
            notificationQueue.add(message)
        }
    }
    private fun hideNotificationDelayed() {
        notificationJob = launch {
            delay(DELAY_BEFORE_HIDE)
            translateViewAnimated(topNotificationTextView, topNotificationTranslation) {
                isNotificationVisible = false
                if (notificationQueue.isNotEmpty()) {
                    notifyOnTop(notificationQueue.poll())
                }
            }
        }
    }

    private fun translateViewAnimated(view: View, translation: Float, finishedCallback: () -> Unit = {}) {
        view.animate()
            .translationY(translation)
            .setStartDelay(ANIMATION_DELAY)
            .setDuration(ANIMATION_DURATION)
            .setInterpolator(LinearInterpolator())
            .addEndAction { finishedCallback.invoke() }
            .start()
    }
}
