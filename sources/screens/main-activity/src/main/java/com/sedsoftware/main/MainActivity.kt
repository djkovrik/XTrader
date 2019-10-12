package com.sedsoftware.main

import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.AppProvider
import com.sedsoftware.core.di.delegate.SnackbarDelegate
import com.sedsoftware.core.presentation.base.BaseActivity
import com.sedsoftware.core.presentation.extension.addEndAction
import com.sedsoftware.core.presentation.extension.launch
import com.sedsoftware.core.presentation.extension.setBackgroundColor
import com.sedsoftware.core.presentation.listener.SwipeToDismissTouchListener
import com.sedsoftware.core.presentation.listener.SwipeToDismissTouchListener.DismissCallbacks
import com.sedsoftware.main.di.ActivityComponent
import com.sedsoftware.screens.main.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

class MainActivity : BaseActivity(), IHasComponent<ActivityComponent>, SnackbarDelegate {

    companion object {
        // Animation
        private const val ANIMATION_DELAY = 100L
        private const val ANIMATION_DURATION = 200L
        private const val DELAY_BEFORE_HIDE = 2000L
    }

    override val appProvider: AppProvider
        get() = (applicationContext as App).getAppComponent()

    override val activityToolsProvider: ActivityToolsProvider
        get() = XInjectionManager.findComponent()

    private val navigator: Navigator =
        object : SupportAppNavigator(this, supportFragmentManager, R.id.mainContainer) {
            override fun setupFragmentTransaction(
                command: Command?,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                fragmentTransaction.setReorderingAllowed(true)
            }
        }

    private var topNotificationTranslation = 0f

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var launcher: MainAppLauncher

    override fun onCreate(savedInstanceState: Bundle?) {

        XInjectionManager
            .bindComponent(this)
            .inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setupViews()

        if (savedInstanceState == null) {
            launcher.coldStart()
        }
    }

    override fun getComponent(): ActivityComponent =
        ActivityComponent.Initializer.init(appProvider, this)

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

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        notificationJob?.cancel()
        super.onPause()
    }

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
