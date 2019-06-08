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
import com.sedsoftware.core.presentation.extension.launch
import com.sedsoftware.core.presentation.extension.setBackgroundColor
import com.sedsoftware.screens.main.di.MainActivityComponent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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
                        notificationJob?.cancel()
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

    override fun getActivityToolsProvider(): MainActivityToolsProvider =
        mainActivityComponent

    override fun notify(textResId: Int) {
        notification_top_text.setText(textResId)
        notificationJob = launch {
            translateViewAnimated(notification_top_text, 0f)
            delay(DELAY_BEFORE_HIDE)
            hideNotification()
        }
    }

    override fun notify(prefixTextResId: Int, message: String?) {
        notification_top_text.text = getString(prefixTextResId, message)
        notificationJob = launch {
            translateViewAnimated(notification_top_text, 0f)
            delay(DELAY_BEFORE_HIDE)
            hideNotification()
        }
    }

    private fun hideNotification() {
        translateViewAnimated(notification_top_text, topNotificationTranslation)
    }

    private fun translateViewAnimated(view: View, translation: Float) {
        view.animate()
            .translationY(translation)
            .setStartDelay(ANIMATION_DELAY)
            .setDuration(ANIMATION_DURATION)
            .setInterpolator(LinearInterpolator())
            .start()
    }

    private companion object {
        const val ANIMATION_DELAY = 100L
        const val ANIMATION_DURATION = 200L
        const val DELAY_BEFORE_HIDE = 3000L
    }
}
