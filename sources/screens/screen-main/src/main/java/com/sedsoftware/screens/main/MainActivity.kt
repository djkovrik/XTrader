package com.sedsoftware.screens.main

import com.sedsoftware.core.di.holder.ActivityComponentHolder
import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.presentation.base.BaseActivity
import com.sedsoftware.screens.main.di.MainActivityComponent

class MainActivity : BaseActivity(), ActivityComponentHolder {

    private val mainActivityComponent: MainActivityToolsProvider by lazy {
        MainActivityComponent.Initializer.init(appComponent)
    }

    override val layoutResId: Int
        get() = R.layout.activity_main

    override fun inject() {
        mainActivityComponent.inject(this@MainActivity)
    }

    override fun getActivityComponent(): MainActivityToolsProvider =
        mainActivityComponent
}
