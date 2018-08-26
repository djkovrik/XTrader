package ${providerPackageName}.di

import ${corePackageName}.${applicationInterface}
import ${corePackageName}.di.provider.${providerName}Provider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ${providerName}Module::class
    ]
)
interface ${providerName}Component : ${providerName}Provider {

    @Component.Builder
    interface Builder {

        fun build(): ${providerName}Component

        @BindsInstance
        fun app(app: ${applicationInterface}): Builder
    }

    class Initializer private constructor() {
        companion object {

            fun init(app: ${applicationInterface}): ${providerName}Provider =
                Dagger${providerName}Component.builder()
                    .app(app)
                    .build()
        }
    }
}
