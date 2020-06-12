package com.sedsoftware.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sedsoftware.core.domain.tools.CiceroneManager
import com.sedsoftware.core.presentation.base.BaseActivity
import com.sedsoftware.core.presentation.delegate.SnackbarDelegate
import com.sedsoftware.core.presentation.extension.addEndAction
import com.sedsoftware.core.presentation.extension.launch
import com.sedsoftware.core.presentation.extension.setBackgroundColor
import com.sedsoftware.core.presentation.listener.SwipeToDismissTouchListener
import com.sedsoftware.core.presentation.listener.SwipeToDismissTouchListener.DismissCallbacks
import com.sedsoftware.main.flows.AppFlow
import com.sedsoftware.screens.main.R
import com.sedsoftware.screens.main.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity(), SnackbarDelegate {

    private val navigator: Navigator =
        object : SupportAppNavigator(this, supportFragmentManager, R.id.mainContainer) {
            override fun setupFragmentTransaction(
                command: Command,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                fragmentTransaction.setReorderingAllowed(true)
            }
        }

    private var topNotificationTranslation = 0f

    @Inject
    lateinit var ciceroneManager: CiceroneManager

    @Inject
    lateinit var launcher: MainAppLauncher

    private lateinit var binding: ActivityMainBinding

    private val navigatorHolder: NavigatorHolder by lazy {
        ciceroneManager.getNavigatorHolder(AppFlow.GLOBAL)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()

        if (savedInstanceState == null) {
            launcher.coldStart()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupViews() {
        setBackgroundColor(R.color.colorBackground)

        binding.topNotificationTextView.post {
            topNotificationTranslation = -binding.topNotificationTextView.measuredHeight.toFloat()
            binding.topNotificationTextView.translationY = topNotificationTranslation
        }

        binding.topNotificationTextView.setOnTouchListener(
            SwipeToDismissTouchListener(
                binding.topNotificationTextView,
                object : DismissCallbacks {
                    override fun onDismiss(view: View) {
                        binding.topNotificationTextView.translationY = topNotificationTranslation
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
            binding.topNotificationTextView.text = message
            translateViewAnimated(binding.topNotificationTextView, 0f) {
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
            translateViewAnimated(binding.topNotificationTextView, topNotificationTranslation) {
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
        const val DELAY_BEFORE_HIDE = 2000L
    }
}
