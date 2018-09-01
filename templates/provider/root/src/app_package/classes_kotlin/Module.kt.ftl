package ${providerPackageName}.di
<#if sample>
import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import ${corePackageName}.${providerModuleName}.Logger
import ${corePackageName}.${providerModuleName}.Settings
import ${providerPackageName}.LoggerImpl
import ${providerPackageName}.SettingsImpl
</#if>
<#if cicerone>
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
 </#if>
import com.sedsoftware.${coreModuleName}.${applicationInterface}
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ${providerName}Module {
<#if sample>
    @Provides
    @Singleton
    fun provideLogger(): Logger =
        LoggerImpl()

    @Provides
    @Singleton
    fun provideResources(app: ${applicationInterface}): Resources =
        app.getApplicationContext().resources

    @Provides
    @Singleton
    fun provideSharedPreferences(app: ${applicationInterface}): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(app.getApplicationContext())

    @Provides
    @Singleton
    fun provideSettings(resources: Resources, preferences: SharedPreferences): Settings =
        SettingsImpl(resources, preferences)
</#if>
<#if cicerone>
    @Provides
    @Singleton
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.navigatorHolder

    @Provides
    @Singleton
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router
</#if>
}
