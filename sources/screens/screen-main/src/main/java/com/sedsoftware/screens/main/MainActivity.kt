package com.sedsoftware.screens.main

import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavController.OnDestinationChangedListener
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.delegate.SnackbarDelegate
import com.sedsoftware.core.di.holder.ActivityToolsHolder
import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.navigation.NavControllerHolder
import com.sedsoftware.core.presentation.SwipeToDismissTouchListener
import com.sedsoftware.core.presentation.SwipeToDismissTouchListener.DismissCallbacks
import com.sedsoftware.core.presentation.extension.addEndAction
import com.sedsoftware.core.presentation.extension.launch
import com.sedsoftware.core.presentation.extension.setBackgroundColor
import com.sedsoftware.screens.main.di.MainActivityComponent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import java.util.LinkedList
import java.util.Queue
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ActivityToolsHolder, SnackbarDelegate {

    @Inject
    lateinit var navControllerHolder: NavControllerHolder

    private val mainActivityComponent: MainActivityComponent by lazy {
        val appComponent = (applicationContext as App).getAppComponent()
        MainActivityComponent.Initializer.init(appComponent, this)
    }

    private val visibleNavigationScreens = setOf(
        R.id.walletScreenFragment,
        R.id.ordersScreenFragment,
        R.id.marketScreenFragment,
        R.id.trackerScreenFragment,
        R.id.toolsScreenFragment
    )

    private val destinationChangeListener = OnDestinationChangedListener { _, destination, _ ->
        showBottomNavigation(visibleNavigationScreens.contains(destination.id))
    }

    private var controller: NavController? = null
    private var notificationJob: Job? = null
    private var topNotificationTranslation = 0f
    private var isNotificationVisible = false
    private val notificationQueue: Queue<String> = LinkedList()

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivityComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setBackgroundColor(R.color.colorBackground)
        controller = findNavController(R.id.nav_controller_main)

        setupTopNotification()
        setupBottomNavigation()
    }

    private fun setupTopNotification() {
        notification_top_text.post {
            topNotificationTranslation = -notification_top_text.measuredHeight.toFloat()
            notification_top_text.translationY = topNotificationTranslation
        }

        notification_top_text.setOnTouchListener(
            SwipeToDismissTouchListener(
                notification_top_text,
                object : DismissCallbacks {
                    override fun onDismiss(view: View) {
                        notification_top_text.translationY = topNotificationTranslation
                        notificationJob?.cancelChildren()
                        notificationQueue.clear()
                    }
                }
            ))
    }

    private fun setupBottomNavigation() {
        controller?.let { bottom_navigation.setupWithNavController(it) }
    }

    private fun showBottomNavigation(show: Boolean) {
        if (show) {
            bottom_navigation.isVisible = true
        } else {
            bottom_navigation.isGone = true
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        controller?.let { navControllerHolder.setNavController(it) }
        controller?.addOnDestinationChangedListener(destinationChangeListener)
    }

    override fun onPause() {
        controller?.removeOnDestinationChangedListener(destinationChangeListener)
        navControllerHolder.removeNavController()
        super.onPause()
    }

    override fun onDetachedFromWindow() {
        notificationJob?.cancel()
        super.onDetachedFromWindow()
    }

    override fun getActivityToolsProvider(): MainActivityToolsProvider =
        mainActivityComponent

    override fun notifyOnTop(message: String) {
        if (!isNotificationVisible) {
            notification_top_text.text = message
            translateViewAnimated(notification_top_text, 0f) {
                isNotificationVisible = true
                hideNotification()
            }
        } else {
            notificationQueue.add(message)
        }
    }

    private fun hideNotification() {
        notificationJob = launch {
            delay(DELAY_BEFORE_HIDE)
            translateViewAnimated(notification_top_text, topNotificationTranslation) {
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

    private companion object {
        const val ANIMATION_DELAY = 100L
        const val ANIMATION_DURATION = 200L
        const val DELAY_BEFORE_HIDE = 3000L
    }
}
