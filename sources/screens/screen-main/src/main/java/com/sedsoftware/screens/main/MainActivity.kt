package com.sedsoftware.screens.main

import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sedsoftware.core.di.holder.ActivityToolsHolder
import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.presentation.base.BaseActivity
import com.sedsoftware.core.di.holder.NavControllerHolder
import com.sedsoftware.screens.main.di.MainActivityComponent

class MainActivity : BaseActivity(), ActivityToolsHolder, NavControllerHolder {

    private val mainActivityComponent: MainActivityComponent by lazy {
        MainActivityComponent.Initializer.init(appComponent, this@MainActivity)
    }

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun inject() {
        mainActivityComponent.inject(this)
    }

    override fun getActivityToolsProvider(): MainActivityToolsProvider =
        mainActivityComponent

    override fun getNavController(): NavController =
        Navigation.findNavController(this, R.id.nav_controller_main)
}
