package com.sedsoftware.screens.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sedsoftware.core.device.api.Logger
import com.sedsoftware.core.presentation.base.BaseActivity
import com.sedsoftware.core.presentation.navigation.NavControllerHolder
import com.sedsoftware.screens.main.di.MainActivityComponent
import com.sedsoftware.screens.main.di.MainActivityComponentHolder
import com.sedsoftware.screens.main.di.MainActivityToolsProvider
import javax.inject.Inject

class MainActivity : BaseActivity(), NavControllerHolder, MainActivityComponentHolder {

    @Inject
    lateinit var logger: Logger

    private val activityComponent: MainActivityToolsProvider by lazy {
        MainActivityComponent.Initializer.init(appComponent, this)
    }

    override fun inject() {
        activityComponent.inject(this@MainActivity)
    }

    override fun getLayoutId(): Int =
        R.layout.activity_main

    override fun getNavController(): NavController =
        Navigation.findNavController(this, R.id.nav_controller_main)

    override fun getMainActivityComponent(): MainActivityToolsProvider =
        activityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.d("MainActivity started.")
    }
}
