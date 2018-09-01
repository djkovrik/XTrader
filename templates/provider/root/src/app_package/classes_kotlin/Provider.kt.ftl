package ${corePackageName}.di.provider
<#if sample>
import ${corePackageName}.${applicationInterface}
import ${corePackageName}.${providerModuleName}.Logger
import ${corePackageName}.${providerModuleName}.Settings
</#if>
<#if cicerone>
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
 </#if>
// FIXME(1) Add this interface to ${applicationInterface}Provider.kt
interface ${providerName}Provider {
<#if sample>
    fun provideContext(): ${applicationInterface}

    fun provideLogger(): Logger

    fun provideSettings(): Settings
</#if>

<#if cicerone>
    fun provideNavigatorHolder(): NavigatorHolder

    fun provideRouter(): Router
</#if>
}
