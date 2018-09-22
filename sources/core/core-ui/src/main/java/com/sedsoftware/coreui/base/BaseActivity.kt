package com.sedsoftware.coreui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sedsoftware.coredi.App
import com.sedsoftware.coredi.provider.ApplicationProvider

abstract class BaseActivity : AppCompatActivity() {

    abstract fun inject()
    abstract fun getLayoutId(): Int

    protected val appComponent: ApplicationProvider by lazy(mode = LazyThreadSafetyMode.NONE) {
        (applicationContext as App).getAppComponent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }
}
