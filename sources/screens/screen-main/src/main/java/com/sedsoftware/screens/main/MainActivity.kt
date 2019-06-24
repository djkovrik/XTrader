package com.sedsoftware.screens.main

import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.delegate.SnackbarDelegate
import com.sedsoftware.core.di.holder.ActivityToolsHolder
import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.presentation.SwipeToDismissTouchListener
import com.sedsoftware.core.presentation.SwipeToDismissTouchListener.DismissCallbacks
import com.sedsoftware.core.presentation.base.BaseActivity
import com.sedsoftware.core.presentation.extension.addEndAction
import com.sedsoftware.core.presentation.extension.failure
import com.sedsoftware.core.presentation.extension.launch
import com.sedsoftware.core.presentation.extension.observe
import com.sedsoftware.core.presentation.extension.setBackgroundColor
import com.sedsoftware.core.presentation.extension.viewModel
import com.sedsoftware.core.utils.common.Failure
import com.sedsoftware.screens.main.di.MainActivityComponent
import com.sedsoftware.screens.main.navigation.NavControllerHolder
import com.sedsoftware.screens.main.navigation.NavigationFlow
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import javax.inject.Inject

class MainActivity : BaseActivity(), ActivityToolsHolder, SnackbarDelegate {

    private val mainActivityComponent: MainActivityComponent by lazy {
        val appComponent = (applicationContext as App).getAppComponent()
        MainActivityComponent.Initializer.init(appComponent, this)
    }

    @Inject
    lateinit var navControllerHolder: NavControllerHolder

    private lateinit var mainActivityViewModel: MainActivityViewModel

    private var introNavHostFragment: NavHostFragment? = null
    private var pinNavHostFragment: NavHostFragment? = null

    private var topNotificationTranslation = 0f
    private var bottomNavigationViewTranslation = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivityComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setBackgroundColor(R.color.colorBackground)

        mainActivityViewModel = viewModel(viewModelFactory) {
            observe(currentNavController, ::handleNavController)
            observe(currentNavFlow, ::handleNavFlow)
            failure(viewModelFailure, ::displayFailure)
        }

        setupViews()

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupViews() {
        bottom_navigation.post {
            bottomNavigationViewTranslation = bottom_navigation.measuredHeight.toFloat()
            bottom_navigation.translationY = bottomNavigationViewTranslation
        }

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

    override fun onResumeFragments() {
        super.onResumeFragments()
        mainActivityViewModel.controller?.let { navControllerHolder.setNavController(it) }
    }

    override fun onPause() {
        notificationJob?.cancel()
        navControllerHolder.removeNavController()
        super.onPause()
    }

    override fun onSupportNavigateUp(): Boolean {
        return mainActivityViewModel.controller?.navigateUp() ?: false
    }

    override fun getActivityToolsProvider(): MainActivityToolsProvider =
            mainActivityComponent

    override fun notifyOnTop(message: String) {
        if (!isNotificationVisible) {
            notification_top_text.text = message
            translateViewAnimated(notification_top_text, 0f) {
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

    private fun setupBottomNavigationBar() {

    }

    private fun handleNavController(navController: NavController?) {

    }

    private fun handleNavFlow(navigationFlow: NavigationFlow?) {

    }

    private fun displayFailure(failure: Failure?) {

    }

    private companion object {
        const val ANIMATION_DELAY = 100L
        const val ANIMATION_DURATION = 200L
        const val DELAY_BEFORE_HIDE = 3000L
    }
}
