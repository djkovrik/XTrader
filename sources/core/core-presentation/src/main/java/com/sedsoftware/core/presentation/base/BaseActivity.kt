package com.sedsoftware.core.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.provider.AppProvider

abstract class BaseActivity : AppCompatActivity() {

    abstract fun inject()
    abstract fun getLayoutId(): Int

    protected val appComponent: AppProvider by lazy(mode = LazyThreadSafetyMode.NONE) {
        (applicationContext as App).getAppComponent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }
}
