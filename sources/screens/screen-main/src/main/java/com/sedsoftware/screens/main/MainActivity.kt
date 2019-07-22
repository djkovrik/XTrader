package com.sedsoftware.screens.main

import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.delegate.NavigationFlowDelegate
import com.sedsoftware.core.di.delegate.SnackbarDelegate
import com.sedsoftware.core.di.holder.ActivityToolsHolder
import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.presentation.base.BaseActivity
import com.sedsoftware.core.presentation.extension.addEndAction
import com.sedsoftware.core.presentation.extension.failure
import com.sedsoftware.core.presentation.extension.launch
import com.sedsoftware.core.presentation.extension.observe
import com.sedsoftware.core.presentation.extension.setBackgroundColor
import com.sedsoftware.core.presentation.extension.viewModel
import com.sedsoftware.core.presentation.listener.SwipeToDismissTouchListener
import com.sedsoftware.core.presentation.listener.SwipeToDismissTouchListener.DismissCallbacks
import com.sedsoftware.core.utils.type.Failure
import com.sedsoftware.screens.main.di.MainActivityComponent
import com.sedsoftware.screens.main.navigation.NavControllerHolder
import com.sedsoftware.screens.main.navigation.NavigationFlow
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import javax.inject.Inject

class MainActivity : BaseActivity(), ActivityToolsHolder, SnackbarDelegate, NavigationFlowDelegate {

    private val mainActivityComponent: MainActivityComponent by lazy {
        val appComponent = (applicationContext as App).getAppComponent()
        MainActivityComponent.Initializer.init(appComponent, this, this)
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
        setSupportActionBar(toolbar)
        appbar.outlineProvider = null
        setBackgroundColor(R.color.colorBackground)

        mainActivityViewModel = viewModel(viewModelFactory) {
            observe(currentNavController, ::handleNavController)
            observe(currentNavFlow, ::handleNavFlow)
            failure(viewModelFailure, ::displayFailure)
        }

        setupViews()
        initStartupFragments()

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
            bottom_navigation.selectedItemId = R.id.navigation_market
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

    private fun initStartupFragments() {
        introNavHostFragment = obtainNavHostFragment(
            tag = getFragmentTag(1, false),
            graphId = MainActivityViewModel.introNavGraph,
            containerId = R.id.nav_host_container
        )

        pinNavHostFragment = obtainNavHostFragment(
            tag = getFragmentTag(2, false),
            graphId = MainActivityViewModel.pinNavGraph,
            containerId = R.id.nav_host_container
        )
    }

    private fun setupBottomNavigationBar() {
        if (mainActivityViewModel.currentNavFlow.value != NavigationFlow.MAIN) {
            return
        }

        introNavHostFragment?.let { detachNavHostFragment(it) }
        pinNavHostFragment?.let { detachNavHostFragment(it) }

        mainActivityViewModel.currentNavController = setupWithNavController(
            bottomNavigationView = bottom_navigation,
            navGraphIds = MainActivityViewModel.mainNavGraphs,
            containerId = R.id.nav_host_container
        )
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        mainActivityViewModel.currentNavController.value?.let {
            navControllerHolder.setNavController(it)
        }
    }

    override fun onPause() {
        notificationJob?.cancel()
        navControllerHolder.removeNavController()
        super.onPause()
    }

    override fun onSupportNavigateUp(): Boolean {
        return mainActivityViewModel.currentNavController.value?.navigateUp() ?: false
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

    override fun switchToMainFlow() {
        mainActivityViewModel.currentNavFlow.value = NavigationFlow.MAIN
        setupBottomNavigationBar()
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

    private fun handleNavController(navController: NavController?) {
        navController?.let { setupActionBarWithNavController(it) }
    }

    private fun handleNavFlow(navigationFlow: NavigationFlow?) {
        when (navigationFlow) {
            NavigationFlow.PIN -> {
                introNavHostFragment?.let { detachNavHostFragment(it) }
                pinNavHostFragment?.let { fragment ->
                    attachNavHostFragment(fragment, true)
                    mainActivityViewModel.currentNavController.value = fragment.navController
                }
            }
            NavigationFlow.INTRO -> {
                pinNavHostFragment?.let { detachNavHostFragment(it) }
                introNavHostFragment?.let { fragment ->
                    attachNavHostFragment(fragment, true)
                    mainActivityViewModel.currentNavController.value = fragment.navController
                }
            }
            else -> {
                showBottomNavigationBar(true)
            }
        }
    }

    private fun displayFailure(failure: Failure?) {

    }

    private fun showBottomNavigationBar(show: Boolean) {
        if (show && bottom_navigation.isVisible || !show && bottom_navigation.isGone) {
            return
        }

        if (show) {
            bottom_navigation.isVisible = true
            translateViewAnimated(bottom_navigation, 0f)
        } else {
            bottom_navigation.isGone = true
            translateViewAnimated(bottom_navigation, bottomNavigationViewTranslation)
        }
    }

    private companion object {
        const val ANIMATION_DELAY = 100L
        const val ANIMATION_DURATION = 200L
        const val DELAY_BEFORE_HIDE = 3000L
    }
}
