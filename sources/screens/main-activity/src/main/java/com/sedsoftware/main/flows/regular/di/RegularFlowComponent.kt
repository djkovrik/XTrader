package com.sedsoftware.main.flows.regular.di

import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.RegularFlowToolsProvider
import com.sedsoftware.core.di.scope.FlowScope
import com.sedsoftware.main.flows.regular.RegularFlowFragment
import com.sedsoftware.main.flows.regular.di.module.RegularFlowModule
import com.sedsoftware.main.tabs.MarketTabContainerFragment
import com.sedsoftware.main.tabs.OrdersTabContainerFragment
import com.sedsoftware.main.tabs.ToolsTabContainerFragment
import com.sedsoftware.main.tabs.TrackerTabContainerFragment
import com.sedsoftware.main.tabs.WalletTabContainerFragment
import dagger.Component

@Component(
    dependencies = [
        ActivityToolsProvider::class
    ],
    modules = [
        RegularFlowModule::class
    ]
)
@FlowScope
interface RegularFlowComponent : RegularFlowToolsProvider {

    fun inject(fragment: RegularFlowFragment)
    fun inject(fragment: MarketTabContainerFragment)
    fun inject(fragment: OrdersTabContainerFragment)
    fun inject(fragment: ToolsTabContainerFragment)
    fun inject(fragment: TrackerTabContainerFragment)
    fun inject(fragment: WalletTabContainerFragment)

    @Component.Factory
    interface Factory {
        fun create(activityToolsProvider: ActivityToolsProvider): RegularFlowComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(activityToolsProvider: ActivityToolsProvider): RegularFlowComponent {

                return DaggerRegularFlowComponent
                    .factory()
                    .create(activityToolsProvider)
            }
        }
    }
}
