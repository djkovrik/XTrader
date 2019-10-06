package com.sedsoftware.screens.main.navigation.flow

import com.github.chernovdmitriy.injectionholdercore.ComponentOwner
import com.github.chernovdmitriy.injectionholderx.InjectionHolderX
import com.sedsoftware.core.di.FlowToolsProvider
import com.sedsoftware.core.presentation.base.FlowFragment
import com.sedsoftware.screens.main.di.FlowComponent
import com.sedsoftware.screens.main.navigation.Screens
import ru.terrakok.cicerone.android.support.SupportAppScreen

class IntroFlowFragment : FlowFragment(), ComponentOwner<FlowComponent> {

    companion object {
        fun newInstance(): IntroFlowFragment = IntroFlowFragment()
    }

    override fun getLaunchScreen(): SupportAppScreen = Screens.Intro

    override fun inject(component: FlowComponent) = component.inject(this)

    override fun provideComponent(): FlowComponent =
        FlowComponent.Initializer.init(appComponent)

    override fun getComponentKey(): String = javaClass.name

    override fun getFlowComponent(): FlowToolsProvider =
        InjectionHolderX.instance.getComponent(this)
}
