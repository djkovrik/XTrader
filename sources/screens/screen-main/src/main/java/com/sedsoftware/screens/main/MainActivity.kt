package com.sedsoftware.screens.main

import android.os.Bundle
import com.sedsoftware.core.device.api.Logger
import com.sedsoftware.core.presentation.base.BaseActivity
import com.sedsoftware.screens.main.di.MainActivityComponent
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var logger: Logger

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun inject() {
        MainActivityComponent.Initializer
            .init(appComponent)
            .inject(this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.d("MainActivity started.")
    }
}
