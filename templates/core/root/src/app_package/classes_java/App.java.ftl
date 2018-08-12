package ${corePackageName};

import android.content.Context;
import ${corePackageName}.di.provider.${applicationProviderInterface};

interface ${applicationInterface} {
    Context getApplicationContext();
    ${applicationProviderInterface} getAppComponent();
}
