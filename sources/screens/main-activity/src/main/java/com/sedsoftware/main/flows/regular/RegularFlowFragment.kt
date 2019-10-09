package com.sedsoftware.main.flows.regular

import com.github.chernovdmitriy.injectionholdercore.ComponentOwner
import com.github.chernovdmitriy.injectionholderx.InjectionHolderX
import com.sedsoftware.core.di.RegularFlowToolsProvider
import com.sedsoftware.core.presentation.base.FlowFragment
import com.sedsoftware.main.flows.regular.di.RegularFlowComponent
import com.sedsoftware.screens.main.navigation.Screens
import ru.terrakok.cicerone.android.support.SupportAppScreen

class RegularFlowFragment : FlowFragment(), ComponentOwner<RegularFlowComponent> {

    companion object {
        fun newInstance(): RegularFlowFragment =
            RegularFlowFragment()
    }

    val flowToolsProvider: RegularFlowToolsProvider =
        InjectionHolderX.instance.getComponent(this)

    override val launchScreen: SupportAppScreen =
        Screens.Market


    override fun inject(component: RegularFlowComponent) =
        component.inject(this)

    override fun provideComponent(): RegularFlowComponent =
        RegularFlowComponent.Initializer.init(activityToolsProvider)

    override fun getComponentKey(): String = javaClass.name
}
