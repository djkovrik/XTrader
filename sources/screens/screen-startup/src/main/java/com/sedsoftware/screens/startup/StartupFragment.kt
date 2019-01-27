package com.sedsoftware.screens.startup

import android.os.Bundle
import android.view.View
import com.sedsoftware.core.di.coordinator.StartupCoordinator
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.screens.startup.di.StartupViewComponent
import javax.inject.Inject

class StartupFragment : BaseFragment() {

    @Inject
    lateinit var coordinator: StartupCoordinator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coordinator.navigateToNextScreen()
    }

    override fun getLayoutResId(): Int =
        R.layout.fragment_startup_screen

    override fun inject() {
        StartupViewComponent.Initializer.init(parentActivityComponent)
            .inject(this@StartupFragment)
    }
}
