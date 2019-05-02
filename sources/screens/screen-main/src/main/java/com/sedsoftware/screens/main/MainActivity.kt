package com.sedsoftware.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.delegate.SnackbarDelegate
import com.sedsoftware.core.di.holder.ActivityToolsHolder
import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.navigation.NavControllerHolder
import com.sedsoftware.core.presentation.extension.setBackgroundColor
import com.sedsoftware.screens.main.di.MainActivityComponent
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
    }

    override fun onPause() {
        navControllerHolder.removeNavController()
        super.onPause()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navControllerHolder.setNavController(Navigation.findNavController(this, R.id.nav_controller_main))
    }

    override fun getActivityToolsProvider(): MainActivityToolsProvider =
        mainActivityComponent

    override fun notifyTop(textResId: Int) {
    }

    override fun notifyBottom(textResId: Int) {
    }

    override fun notifyBottomWithAction(textResId: Int, buttonResId: Int, action: () -> Unit) {
    }
}
