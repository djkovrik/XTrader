package com.sedsoftware.main.di

import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.AppProvider
import com.sedsoftware.core.di.scope.ActivityScope
import com.sedsoftware.main.MainActivity
import com.sedsoftware.main.di.module.NavigationModule
import com.sedsoftware.main.flows.RegularFlowFragment
import com.sedsoftware.main.flows.StartingFlowFragment
import com.sedsoftware.main.tabs.MarketTabContainerFragment
import com.sedsoftware.main.tabs.OrdersTabContainerFragment
import com.sedsoftware.main.tabs.ToolsTabContainerFragment
import com.sedsoftware.main.tabs.TrackerTabContainerFragment
import com.sedsoftware.main.tabs.WalletTabContainerFragment
import dagger.Component

@Component(
    dependencies = [
        AppProvider::class
    ],
    modules = [
        NavigationModule::class
    ]
)
@ActivityScope
interface MainActivityComponent : ActivityToolsProvider {

    @Component.Factory
    interface Factory {
        fun create(appProvider: AppProvider): MainActivityComponent
    }

    // Main
    fun inject(activity: MainActivity)

    // Flows
    fun inject(activity: StartingFlowFragment)
    fun inject(fragment: RegularFlowFragment)

    // Containers
    fun inject(fragment: MarketTabContainerFragment)
    fun inject(fragment: OrdersTabContainerFragment)
    fun inject(fragment: ToolsTabContainerFragment)
    fun inject(fragment: TrackerTabContainerFragment)
    fun inject(fragment: WalletTabContainerFragment)

    class Initializer private constructor() {
        companion object {

            fun init(appProvider: AppProvider): MainActivityComponent {

                return DaggerMainActivityComponent
                    .factory()
                    .create(appProvider)
            }
        }
    }
}
