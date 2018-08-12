<recipe>

    <instantiate from="root/src/app_package/classes_kotlin/App.kt.ftl"
        to="${escapeXmlAttribute(topOut)}/${coreModuleName}/src/main/java/${slashedPackageName(corePackageName)}/${applicationInterface}.kt" />

    <instantiate from="root/src/app_package/classes_kotlin/ApplicationProvider.kt.ftl"
        to="${escapeXmlAttribute(topOut)}/${coreModuleName}/src/main/java/${slashedPackageName(corePackageName)}/di/provider/${applicationProviderInterface}.kt" />

    <instantiate from="root/src/app_package/classes_kotlin/ScopeActivity.kt.ftl"
        to="${escapeXmlAttribute(topOut)}/${coreModuleName}/src/main/java/${slashedPackageName(corePackageName)}/di/scope/ActivityScope.kt" />

    <instantiate from="root/src/app_package/classes_kotlin/ScopeFragment.kt.ftl"
        to="${escapeXmlAttribute(topOut)}/${coreModuleName}/src/main/java/${slashedPackageName(corePackageName)}/di/scope/FragmentScope.kt" />

    <instantiate from="root/src/app_package/classes_kotlin/ScopeService.kt.ftl"
        to="${escapeXmlAttribute(topOut)}/${coreModuleName}/src/main/java/${slashedPackageName(corePackageName)}/di/scope/ServiceScope.kt" />

    <instantiate from="root/src/app_package/classes_kotlin/ScopeView.kt.ftl"
        to="${escapeXmlAttribute(topOut)}/${coreModuleName}/src/main/java/${slashedPackageName(corePackageName)}/di/scope/ViewScope.kt" />

</recipe>
