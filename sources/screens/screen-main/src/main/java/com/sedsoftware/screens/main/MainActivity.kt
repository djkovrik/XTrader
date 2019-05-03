package com.sedsoftware.screens.main

import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.delegate.SnackbarDelegate
import com.sedsoftware.core.di.holder.ActivityToolsHolder
import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.navigation.NavControllerHolder
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

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivityComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setBackgroundColor(R.color.colorBackground)

        notification_top_text.post {
            notification_top_text.translationY = -notification_top_text.measuredHeight.toFloat()
        }

        notification_bottom_container.post {
            notification_bottom_container.translationY = notification_bottom_container.measuredHeight.toFloat()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navControllerHolder.setNavController(Navigation.findNavController(this, R.id.nav_controller_main))
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

    override fun notifyWithAction(textResId: Int, buttonResId: Int, action: () -> Unit) {
        notification_bottom_text.setText(textResId)
        notification_bottom_button.setText(buttonResId)
        notification_bottom_button.setOnClickListener { action.invoke() }
        translateViewAnimated(notification_bottom_container, 0f)
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
