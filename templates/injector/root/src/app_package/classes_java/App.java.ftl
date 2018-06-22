package ${packageName};

import android.app.Application;
import ${corePackageName}.${applicationInterface};
import ${corePackageName}.di.provider.${applicationProviderInterface};
import ${packageName}.di.${applicationClass}Component;

public final class ${applicationClass} extends Application implements ${applicationInterface} {

    private static ${applicationInterface}Component appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent.inject(this);
    }

    public static ${applicationInterface}Component getComponent() {
        if (appComponent == null) {
            appComponent = ${applicationInterface}Component.Initializer.init(this);
        }

        return appComponent;
    }
}
