<recipe>

    <#if moxy>

        <instantiate from="root/src/app_package/classes_kotlin/BaseActivityMoxy.kt.ftl"
            to="${escapeXmlAttribute(topOut)}/${coreUiModuleName}/src/main/java/${slashedPackageName(coreUiPackageName)}/base/BaseActivity.kt" />

        <instantiate from="root/src/app_package/classes_kotlin/BaseFragmentMoxy.kt.ftl"
            to="${escapeXmlAttribute(topOut)}/${coreUiModuleName}/src/main/java/${slashedPackageName(coreUiPackageName)}/base/BaseFragment.kt" />

        <instantiate from="root/src/app_package/classes_kotlin/BasePresenterMoxy.kt.ftl"
            to="${escapeXmlAttribute(topOut)}/${coreUiModuleName}/src/main/java/${slashedPackageName(coreUiPackageName)}/base/BasePresenter.kt" />

    <#else>

        <instantiate from="root/src/app_package/classes_kotlin/BaseActivity.kt.ftl"
            to="${escapeXmlAttribute(topOut)}/${coreUiModuleName}/src/main/java/${slashedPackageName(coreUiPackageName)}/base/BaseActivity.kt" />

        <instantiate from="root/src/app_package/classes_kotlin/BaseFragment.kt.ftl"
            to="${escapeXmlAttribute(topOut)}/${coreUiModuleName}/src/main/java/${slashedPackageName(coreUiPackageName)}/base/BaseFragment.kt" />

        <instantiate from="root/src/app_package/classes_kotlin/BasePresenter.kt.ftl"
            to="${escapeXmlAttribute(topOut)}/${coreUiModuleName}/src/main/java/${slashedPackageName(coreUiPackageName)}/base/BasePresenter.kt" />

        <instantiate from="root/src/app_package/classes_kotlin/BaseView.kt.ftl"
            to="${escapeXmlAttribute(topOut)}/${coreUiModuleName}/src/main/java/${slashedPackageName(coreUiPackageName)}/base/BaseView.kt" />

        <instantiate from="root/src/app_package/classes_kotlin/BasePresenterImpl.kt.ftl"
            to="${escapeXmlAttribute(topOut)}/${coreUiModuleName}/src/main/java/${slashedPackageName(coreUiPackageName)}/base/BasePresenterImpl.kt" />

    </#if>

</recipe>
