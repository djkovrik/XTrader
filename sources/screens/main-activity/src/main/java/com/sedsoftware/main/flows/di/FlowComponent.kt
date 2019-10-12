package com.sedsoftware.main.flows.di

import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.FlowToolsProvider
import com.sedsoftware.core.di.scope.FlowScope
import com.sedsoftware.main.flows.RegularFlowFragment
import com.sedsoftware.main.flows.StartingFlowFragment
import dagger.Component

@Component(
    dependencies = [
        ActivityToolsProvider::class
    ],
    modules = [
        FlowModule::class
    ]
)
@FlowScope
interface FlowComponent : FlowToolsProvider {

    fun inject(fragment: StartingFlowFragment)

    fun inject(fragment: RegularFlowFragment)

    @Component.Factory
    interface Factory {
        fun create(activityToolsProvider: ActivityToolsProvider): FlowComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(activityToolsProvider: ActivityToolsProvider): FlowComponent {

                return DaggerFlowComponent
                    .factory()
                    .create(activityToolsProvider)
            }
        }
    }
}
