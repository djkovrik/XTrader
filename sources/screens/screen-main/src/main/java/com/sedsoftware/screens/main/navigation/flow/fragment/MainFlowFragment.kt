package com.sedsoftware.screens.main.navigation.flow.fragment

import com.github.chernovdmitriy.injectionholdercore.ComponentOwner
import com.github.chernovdmitriy.injectionholderx.InjectionHolderX
import com.sedsoftware.core.di.FlowToolsProvider
import com.sedsoftware.core.presentation.base.FlowFragment
import com.sedsoftware.screens.main.di.FlowComponent
import com.sedsoftware.screens.main.navigation.Screens
import ru.terrakok.cicerone.android.support.SupportAppScreen

class MainFlowFragment : FlowFragment(), ComponentOwner<FlowComponent> {

    companion object {
        fun newInstance(): MainFlowFragment =
            MainFlowFragment()
    }

    override val flowToolsProvider: FlowToolsProvider =
        InjectionHolderX.instance.getComponent(this)

    override val launchScreen: SupportAppScreen =
        Screens.Market


    override fun inject(component: FlowComponent) =
        component.inject(this)

    override fun provideComponent(): FlowComponent =
        FlowComponent.Initializer.init(activityToolsProvider)

    override fun getComponentKey(): String = javaClass.name
}
