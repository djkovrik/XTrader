package com.sedsoftware.core.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.provider.AppProvider
import com.sedsoftware.core.presentation.R
import com.sedsoftware.core.presentation.extension.setBackgroundColor

abstract class BaseActivity : AppCompatActivity() {

    protected val appComponent: AppProvider by lazy(mode = LazyThreadSafetyMode.NONE) {
        (applicationContext as App).getAppComponent()
    }

    abstract fun getLayoutResId(): Int

    abstract fun inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        setBackgroundColor(R.color.colorBackground)
    }
}
