package com.sedsoftware.main.flows.starting.di

import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.StartingFlowToolsProvider
import com.sedsoftware.core.di.scope.FlowScope
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

    @Component.Factory
    interface Factory {
        fun create(activityToolsProvider: ActivityToolsProvider): StartingFlowComponent
    }

    fun inject(fragment: StartingFlowFragment)

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
