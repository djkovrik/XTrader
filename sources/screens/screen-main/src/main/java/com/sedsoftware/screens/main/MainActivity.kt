package com.sedsoftware.screens.main

import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.delegate.NavigationFlowDelegate
import com.sedsoftware.core.di.delegate.SnackbarDelegate
import com.sedsoftware.core.di.holder.ActivityToolsHolder
import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.navigation.NavControllerHolder
import com.sedsoftware.core.presentation.SwipeToDismissTouchListener
import com.sedsoftware.core.presentation.SwipeToDismissTouchListener.DismissCallbacks
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.extension.addEndAction
import com.sedsoftware.core.presentation.extension.launch
import com.sedsoftware.core.presentation.extension.setBackgroundColor
import com.sedsoftware.screens.main.di.MainActivityComponent
import com.sedsoftware.screens.startup.StartupFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import java.util.LinkedList
import java.util.Queue
import javax.inject.Inject
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity(), ActivityToolsHolder, NavigationFlowDelegate, SnackbarDelegate {

    @Inject
    lateinit var navControllerHolder: NavControllerHolder

    private val mainActivityComponent: MainActivityComponent by lazy {
        val appComponent = (applicationContext as App).getAppComponent()
        MainActivityComponent.Initializer.init(appComponent, this, this)
    }

    private val notificationQueue: Queue<String> = LinkedList()

    private var currentNavController: MutableLiveData<NavController>? = null
    private var notificationJob: Job? = null

    private var topNotificationTranslation = 0f
    private var bottomNavigationViewTranslation = 0f

    private var isNotificationVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivityComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setBackgroundColor(R.color.colorBackground)

        setupTopNotification()
        setupBottomNavigationInitial()

        val introNavHostFragment = obtainNavHostFragment(
            tag = getFragmentTag(StartupFragment::class),
            graphId = R.navigation.navigation_intro,
            containerId = R.id.nav_host_container
        )

        currentNavController?.value = introNavHostFragment.navController
        navControllerHolder.setNavController(introNavHostFragment.navController)

        attachNavHostFragment(introNavHostFragment, true)
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

    private fun setupBottomNavigationInitial() {
        bottom_navigation.post {
            bottomNavigationViewTranslation = bottom_navigation.measuredHeight.toFloat()
            bottom_navigation.translationY = bottomNavigationViewTranslation
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        currentNavController?.value?.let { navControllerHolder.setNavController(it) }
    }

    override fun onPause() {
        notificationJob?.cancel()
        navControllerHolder.removeNavController()
        super.onPause()
    }

    override fun getActivityToolsProvider(): MainActivityToolsProvider =
        mainActivityComponent

    override fun switchToMainFlow() {

    }

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

    private fun obtainNavHostFragment(tag: String, graphId: Int, containerId: Int): NavHostFragment {

        val existingFragment = supportFragmentManager.findFragmentByTag(tag) as NavHostFragment?
        existingFragment?.let { return it }

        val navHostFragment = NavHostFragment.create(graphId)
        supportFragmentManager.beginTransaction()
            .add(containerId, navHostFragment, tag)
            .commitNow()
        return navHostFragment
    }

    private fun attachNavHostFragment(fragment: NavHostFragment, isPrimary: Boolean) {
        supportFragmentManager.beginTransaction()
            .attach(fragment)
            .apply {
                if (isPrimary) {
                    setPrimaryNavigationFragment(fragment)
                }
            }
            .commitNow()
    }

    private fun detachNavHostFragment(fragment: NavHostFragment) {
        supportFragmentManager.beginTransaction()
            .detach(fragment)
            .commitNow()
    }

    private fun getFragmentTag(kClass: KClass<out BaseFragment>): String =
        kClass.simpleName ?: "emptyTag"

    private companion object {
        const val ANIMATION_DELAY = 100L
        const val ANIMATION_DURATION = 200L
        const val DELAY_BEFORE_HIDE = 3000L
    }
}
