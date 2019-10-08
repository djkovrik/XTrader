package com.sedsoftware.main.flows.starting

import com.github.chernovdmitriy.injectionholdercore.ComponentOwner
import com.github.chernovdmitriy.injectionholderx.InjectionHolderX
import com.sedsoftware.core.di.FlowToolsProvider
import com.sedsoftware.core.di.StartingFlowToolsProvider
import com.sedsoftware.core.presentation.base.FlowFragment
import com.sedsoftware.main.flows.starting.di.StartingFlowComponent
import com.sedsoftware.screens.main.di.FlowComponent
import com.sedsoftware.screens.main.navigation.Screens
import ru.terrakok.cicerone.android.support.SupportAppScreen

class StartingFlowFragment : FlowFragment(), ComponentOwner<StartingFlowComponent> {

    companion object {
        fun newInstance(): StartingFlowFragment =
            StartingFlowFragment()
    }

    val flowToolsProvider: StartingFlowToolsProvider =
        InjectionHolderX.instance.getComponent(this)

    override val launchScreen: SupportAppScreen =
        Screens.Intro


    override fun inject(component: StartingFlowComponent) =
        component.inject(this)

    override fun provideComponent(): StartingFlowComponent =
        StartingFlowComponent.Initializer.init(activityToolsProvider)

    override fun getComponentKey(): String = javaClass.name
}
