package ${screenPackageName}.di

import ${corePackageName}.di.provider.${applicationProviderInterface}
import ${corePackageName}.di.scope.ActivityScope
import ${screenPackageName}.view.${screenClass}
import dagger.Component

@ActivityScope
@Component(
        dependencies = [${applicationProviderInterface}::class],
        modules = [${screenModuleClass}::class]
)
interface ${screenComponentClass} {

    fun inject(${screenType}: ${screenClass})

    class Initializer private constructor() {
        companion object {
            fun init(appComponent: ${applicationProviderInterface}): ${screenComponentClass} =
                    Dagger${screenComponentClass}.builder()
                            // TODO Check app provider name here:
                            .appProvider(appComponent)
                            .build()
        }
    }
}
