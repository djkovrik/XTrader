package com.sedsoftware.screens.main.navigation.flow

import com.github.chernovdmitriy.injectionholdercore.ComponentOwner
import com.sedsoftware.core.presentation.base.FlowFragment
import com.sedsoftware.screens.main.di.FlowComponent
import com.sedsoftware.screens.main.navigation.Screens
import ru.terrakok.cicerone.android.support.SupportAppScreen

class MainFlowFragment : FlowFragment(), ComponentOwner<FlowComponent> {

    companion object {
        fun newInstance(): MainFlowFragment = MainFlowFragment()
    }

    override fun getLaunchScreen(): SupportAppScreen = Screens.Market

    override fun inject(component: FlowComponent) = component.inject(this)

    override fun provideComponent(): FlowComponent =
        FlowComponent.Initializer.init(appComponent)

    override fun getComponentKey(): String = javaClass.name
}
