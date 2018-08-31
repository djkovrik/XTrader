package ${packageName}.di

import ${corePackageName}.di.provider.${applicationProviderInterface}
import ${packageName}.${applicationClass}
import dagger.Component
import javax.inject.Singleton

// FIXME(2) Add providers to dependencies here:
@Component(dependencies = [])
@Singleton
interface ${applicationInterface}Component : ${applicationProviderInterface} {

    fun inject(app: ${applicationClass})

    class Initializer private constructor() {
        companion object {

            fun init(app: ${applicationClass}): ${applicationInterface}Component {
            // FIXME(3) Init providers here:
            // val someProvider = SomeComponent.Initializer.init(app)

            return DaggerAppComponent.builder()
              // .someProvider(someProvider)
              .build()
            }
        }
    }
}
