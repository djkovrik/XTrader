package ${packageName}.di

import ${corePackageName}.di.provider.${applicationProviderInterface}
import ${packageName}.${applicationClass}
import dagger.Component
import javax.inject.Singleton

@Component(dependencies = [/*Add component dependencies here*/])
@Singleton
interface ${applicationInterface}Component : ${applicationProviderInterface} {

    fun inject(app: ${applicationClass})

    class Initializer private constructor() {
        companion object {

            fun init(app: ${applicationClass}): ${applicationInterface}Component {
            /* Init providers here */
            /* val someProvider = SomeComponent.Initializer.init(app) */

            return DaggerAppComponent.builder()
              /* .someProvider(someProvider) */
              .build()
            }
        }
    }
}
