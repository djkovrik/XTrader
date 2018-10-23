package com.sedsoftware.screens.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sedsoftware.core.device.api.Logger
import com.sedsoftware.core.di.holder.ActivityComponentHolder
import com.sedsoftware.core.di.holder.NavControllerHolder
import com.sedsoftware.core.di.provider.ActivityToolsProvider
import com.sedsoftware.core.presentation.base.BaseActivity
import com.sedsoftware.screens.main.di.MainActivityComponent
import javax.inject.Inject

class MainActivity : BaseActivity(), ActivityComponentHolder, NavControllerHolder {

    @Inject
    lateinit var logger: Logger

    private val mainActivityComponent: ActivityToolsProvider by lazy {
        MainActivityComponent.Initializer.init(appComponent, this)
    }

    override fun inject() {
        mainActivityComponent.inject(this@MainActivity)
    }

    override fun getLayoutId(): Int =
        R.layout.activity_main

    override fun getNavController(): NavController =
        Navigation.findNavController(this, R.id.nav_controller_main)

    override fun getActivityComponent(): ActivityToolsProvider =
        mainActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.d("MainActivity started.")
    }
}
