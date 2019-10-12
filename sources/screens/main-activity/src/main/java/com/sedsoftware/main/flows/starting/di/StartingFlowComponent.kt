package com.sedsoftware.main.flows.starting.di

import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.StartingFlowToolsProvider
import com.sedsoftware.core.di.scope.FlowScope
import com.sedsoftware.main.flows.di.StartingFlowModule
import com.sedsoftware.main.flows.starting.StartingFlowFragment
import dagger.Component

@Component(
    dependencies = [
        ActivityToolsProvider::class
    ],
    modules = [
        StartingFlowModule::class
    ]
)
@FlowScope
interface StartingFlowComponent : StartingFlowToolsProvider {

    fun inject(fragment: StartingFlowFragment)

    @Component.Factory
    interface Factory {
        fun create(activityToolsProvider: ActivityToolsProvider): StartingFlowComponent
    }

    class Initializer private constructor() {
        companion object {

            fun init(activityToolsProvider: ActivityToolsProvider): StartingFlowComponent {

                return DaggerStartingFlowComponent
                    .factory()
                    .create(activityToolsProvider)
            }
        }
    }
}
