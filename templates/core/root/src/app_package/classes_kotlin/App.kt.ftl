package ${corePackageName}

import android.content.Context
import ${corePackageName}.di.provider.${applicationProviderInterface}

interface ${corePackageName} {
    fun getApplicationContext(): Context
    fun getAppComponent(): ${applicationProviderInterface}
}
