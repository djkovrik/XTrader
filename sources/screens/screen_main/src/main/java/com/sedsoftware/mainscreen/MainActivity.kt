package com.sedsoftware.mainscreen

import android.os.Bundle
import com.sedsoftware.core.App
import com.sedsoftware.core.device.Logger
import com.sedsoftware.mainscreen.di.MainActivityComponent
import com.sedsoftware.coreui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var logger: Logger

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun inject() {
        MainActivityComponent.Initializer
            .init((applicationContext as App).getAppComponent())
            .inject(this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.d("MainActivity started.")
    }
}
