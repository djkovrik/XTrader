package com.sedsoftware.main.flows.regular.di

import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.scope.FlowScope
import com.sedsoftware.main.flows.regular.RegularFlowFragment
import dagger.Component

@Component(
    dependencies = [
        ActivityToolsProvider::class
    ]
)
@FlowScope
interface RegularFlowComponent {

    fun inject(fragment: RegularFlowFragment)

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
