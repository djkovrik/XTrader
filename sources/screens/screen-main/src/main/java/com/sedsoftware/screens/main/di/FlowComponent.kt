package com.sedsoftware.screens.main.di

import com.sedsoftware.core.di.AppProvider
import com.sedsoftware.core.di.FlowToolsProvider
import com.sedsoftware.core.di.scope.FlowScope
import com.sedsoftware.core.presentation.base.FlowFragment
import com.sedsoftware.screens.main.di.module.FlowModule
import com.sedsoftware.screens.main.di.module.FlowViewModelsModule
import dagger.Component

@Component(
    dependencies = [
        AppProvider::class
    ],
    modules = [
        FlowModule::class,
        FlowViewModelsModule::class
    ]
)
@FlowScope
interface FlowComponent : FlowToolsProvider {

    @Component.Factory
    interface Factory {
        fun create(appProvider: AppProvider): FlowComponent
    }

    fun inject(fragment: FlowFragment)

    class Initializer private constructor() {
        companion object {

            fun init(appProvider: AppProvider): FlowComponent {

                return DaggerFlowComponent
                    .factory()
                    .create(appProvider)
            }
        }
    }
}
