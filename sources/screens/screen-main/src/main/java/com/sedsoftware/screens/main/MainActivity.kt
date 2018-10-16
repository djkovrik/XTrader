package com.sedsoftware.screens.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sedsoftware.core.device.api.Logger
import com.sedsoftware.core.presentation.base.BaseActivity
import com.sedsoftware.core.presentation.navigation.NavControllerProvider
import com.sedsoftware.screens.main.di.MainActivityComponent
import javax.inject.Inject

class MainActivity : BaseActivity(), NavControllerProvider {

    @Inject
    lateinit var logger: Logger

    override val navController: NavController
        get() = Navigation.findNavController(this, R.id.nav_controller_main)

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun inject() {
        MainActivityComponent.Initializer
            .init(appComponent, this)
            .inject(this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.d("MainActivity started.")
    }
}
