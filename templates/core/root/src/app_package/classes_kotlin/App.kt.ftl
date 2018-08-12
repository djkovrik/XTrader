package ${corePackageName}

import android.content.Context
import ${corePackageName}.di.provider.${applicationProviderInterface}

interface ${applicationInterface} {
    fun getApplicationContext(): Context
    fun getAppComponent(): ${applicationProviderInterface}
}
