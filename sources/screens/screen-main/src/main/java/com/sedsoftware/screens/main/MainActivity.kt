package com.sedsoftware.screens.main

import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.delegate.SnackbarDelegate
import com.sedsoftware.core.di.holder.ActivityToolsHolder
import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.navigation.NavControllerHolder
import com.sedsoftware.core.presentation.SwipeToDismissTouchListener
import com.sedsoftware.core.presentation.SwipeToDismissTouchListener.DismissCallbacks
import com.sedsoftware.core.presentation.extension.setBackgroundColor
import com.sedsoftware.screens.main.di.MainActivityComponent
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ActivityToolsHolder, SnackbarDelegate {

    private val mainActivityComponent: MainActivityComponent by lazy {
        val appComponent = (applicationContext as App).getAppComponent()
        MainActivityComponent.Initializer.init(appComponent, this)
    }

    @Inject
    lateinit var navControllerHolder: NavControllerHolder

    private var controller: NavController? = null
    private var topNotificationTranslation = 0f
    private var bottomNavigationTranslation = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivityComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setBackgroundColor(R.color.colorBackground)

        notification_top_text.post {
            topNotificationTranslation = -notification_top_text.measuredHeight.toFloat()
            notification_top_text.translationY = topNotificationTranslation
        }

//        bottom_navigation.post {
//            bottomNavigationTranslation = bottom_navigation.measuredHeight.toFloat()
//            bottom_navigation.translationY = bottomNavigationTranslation
//        }

        notification_top_text.setOnTouchListener(
            SwipeToDismissTouchListener(
                notification_top_text,
                object : DismissCallbacks {
                    override fun onDismiss(view: View) {
                        notification_top_text.translationY = topNotificationTranslation
                    }
                }
            ))
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        controller = findNavController(R.id.nav_controller_main)
        controller?.let { navControllerHolder.setNavController(it) }

    }

    override fun onPause() {
        navControllerHolder.removeNavController()
        super.onPause()
    }

    override fun getActivityToolsProvider(): MainActivityToolsProvider =
        mainActivityComponent

    override fun notify(textResId: Int) {
        notification_top_text.setText(textResId)
        translateViewAnimated(notification_top_text, 0f)
    }

    private fun showBottomNavigation() {

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
    }
}
