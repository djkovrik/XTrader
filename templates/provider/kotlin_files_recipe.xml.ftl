<recipe>

    <instantiate from="root/src/app_package/classes_kotlin/Component.kt.ftl"
        to="${escapeXmlAttribute(topOut)}/${providerModuleName}/src/main/java/${slashedPackageName(providerPackageName)}/di/${providerName}Component.kt" />

    <#if includeSample>

        <instantiate from="root/src/app_package/classes_kotlin/Logger.kt.ftl"
            to="${escapeXmlAttribute(topOut)}/${coreModuleName}/src/main/java/${slashedPackageName(corePackageName)}/${providerModuleName}/Logger.kt" />

        <instantiate from="root/src/app_package/classes_kotlin/LoggerImpl.kt.ftl"
            to="${escapeXmlAttribute(topOut)}/${providerModuleName}/src/main/java/${slashedPackageName(providerPackageName)}/LoggerImpl.kt" />

    </#if>

    <instantiate from="root/src/app_package/classes_kotlin/Module.kt.ftl"
        to="${escapeXmlAttribute(topOut)}/${providerModuleName}/src/main/java/${slashedPackageName(providerPackageName)}/di/${providerName}Module.kt" />

    <instantiate from="root/src/app_package/classes_kotlin/Provider.kt.ftl"
        to="${escapeXmlAttribute(topOut)}/${coreModuleName}/src/main/java/${slashedPackageName(corePackageName)}/di/provider/${providerName}Provider.kt" />

    <#if includeSample>

        <instantiate from="root/src/app_package/classes_kotlin/Settings.kt.ftl"
            to="${escapeXmlAttribute(topOut)}/${coreModuleName}/src/main/java/${slashedPackageName(corePackageName)}/${providerModuleName}/Settings.kt" />

        <instantiate from="root/src/app_package/classes_kotlin/SettingsImpl.kt.ftl"
            to="${escapeXmlAttribute(topOut)}/${providerModuleName}/src/main/java/${slashedPackageName(providerPackageName)}/SettingsImpl.kt" />

    </#if>

</recipe>
