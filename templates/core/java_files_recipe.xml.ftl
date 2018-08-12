<recipe>

    <instantiate from="root/src/app_package/classes_java/App.java.ftl"
        to="${escapeXmlAttribute(topOut)}/${coreModuleName}/src/main/java/${slashedPackageName(corePackageName)}/${applicationInterface}.java" />

    <instantiate from="root/src/app_package/classes_java/ApplicationProvider.java.ftl"
        to="${escapeXmlAttribute(topOut)}/${coreModuleName}/src/main/java/${slashedPackageName(corePackageName)}/di/provider/${applicationProviderInterface}.java" />

    <instantiate from="root/src/app_package/classes_java/ScopeActivity.java.ftl"
        to="${escapeXmlAttribute(topOut)}/${coreModuleName}/src/main/java/${slashedPackageName(corePackageName)}/di/scope/ActivityScope.java" />

    <instantiate from="root/src/app_package/classes_java/ScopeFragment.java.ftl"
        to="${escapeXmlAttribute(topOut)}/${coreModuleName}/src/main/java/${slashedPackageName(corePackageName)}/di/scope/FragmentScope.java" />

    <instantiate from="root/src/app_package/classes_java/ScopeService.java.ftl"
        to="${escapeXmlAttribute(topOut)}/${coreModuleName}/src/main/java/${slashedPackageName(corePackageName)}/di/scope/ServiceScope.java" />

    <instantiate from="root/src/app_package/classes_java/ScopeView.java.ftl"
        to="${escapeXmlAttribute(topOut)}/${coreModuleName}/src/main/java/${slashedPackageName(corePackageName)}/di/scope/ViewScope.java" />

</recipe>
