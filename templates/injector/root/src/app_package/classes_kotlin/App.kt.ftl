package ${packageName}

import android.app.Application
import ${corePackageName}.${applicationInterface}
import ${corePackageName}.di.provider.${applicationProviderInterface}
import ${packageName}.di.${applicationClass}Component

class ${applicationClass} : Application(), ${applicationInterface} {

    private val appComponent: ${applicationInterface}Component by lazy { ${applicationInterface}Component.Initializer.init(this@${applicationClass}) }

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)
    }

    override fun getAppComponent(): ${applicationProviderInterface} = appComponent
}
