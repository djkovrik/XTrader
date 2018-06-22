package ${packageName}.di;

import ${corePackageName}.di.provider.${applicationProviderInterface};
import ${packageName}.${applicationClass};
import dagger.Component;
import javax.inject.Singleton;

@Component(dependencies = [/*Add component dependencies here*/])
@Singleton
interface ${applicationInterface}Component implements ${applicationProviderInterface} {

    void inject(${applicationClass} app);

    class Initializer {
      public static ${applicationInterface}Component init(${applicationClass} app) {
        /* Init providers here */
        /* someProvider SomeProvider = SomeComponent.Initializer.init(app); */

        return DaggerAppComponent.builder()
          /* .someProvider(someProvider) */
          .build();
      }
    }
}
