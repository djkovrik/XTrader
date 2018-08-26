package ${providerPackageName}.di
<#if includeSample>
import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
</#if>
import com.sedsoftware.${coreModuleName}.${applicationInterface}
<#if includeSample>
import ${corePackageName}.${providerModuleName}.Logger
import ${corePackageName}.${providerModuleName}.Settings
import ${providerPackageName}.LoggerImpl
import ${providerPackageName}.SettingsImpl
</#if>
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ${providerName}Module {
<#if includeSample>
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
}
