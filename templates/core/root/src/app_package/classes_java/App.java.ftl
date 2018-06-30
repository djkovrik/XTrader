package ${corePackageName};

import android.content.Context;
import ${corePackageName}.di.provider.${applicationProviderInterface};

interface ${corePackageName} {
    Context getApplicationContext();
    ${applicationProviderInterface} getAppComponent();
}
