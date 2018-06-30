<recipe>

    <instantiate from="root/src/app_package/classes_kotlin/App.kt.ftl"
        to="${escapeXmlAttribute(srcOut)}/${applicationInterface}.kt" />

    <instantiate from="root/src/app_package/classes_kotlin/ApplicationProvider.kt.ftl"
        to="${escapeXmlAttribute(srcOut)}/di/provider/${applicationProviderInterface}.kt" />

    <instantiate from="root/src/app_package/classes_kotlin/ScopeActivity.kt.ftl"
        to="${escapeXmlAttribute(srcOut)}/di/scope/ActivityScope.kt" />

    <instantiate from="root/src/app_package/classes_kotlin/ScopeFragment.kt.ftl"
        to="${escapeXmlAttribute(srcOut)}/di/scope/FragmentScope.kt" />

    <instantiate from="root/src/app_package/classes_kotlin/ScopeService.kt.ftl"
        to="${escapeXmlAttribute(srcOut)}/di/scope/ServiceScope.kt" />

    <instantiate from="root/src/app_package/classes_kotlin/ScopeView.kt.ftl"
        to="${escapeXmlAttribute(srcOut)}/di/scope/ViewScope.kt" />

</recipe>
