package ${corePackageName}.di.provider
<#if includeSample>
import ${corePackageName}.${applicationInterface}
import ${corePackageName}.${providerModuleName}.Logger
import ${corePackageName}.${providerModuleName}.Settings
</#if>
// FIXME Add this interface to ${applicationInterface}Provider.kt
interface ${providerName}Provider {
<#if includeSample>
    fun provideContext(): ${applicationInterface}

    fun provideLogger(): Logger

    fun provideSettings(): Settings
</#if>
}
