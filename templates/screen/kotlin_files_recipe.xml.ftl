<recipe>
    <#if moxy>

        <#if screenType == 'activity'>

            <instantiate from="root/src/app_package/classes_kotlin/ActivityMoxy.kt.ftl"
                to="${escapeXmlAttribute(topOut)}/${screenModuleName}/src/main/java/${slashedPackageName(screenPackageName)}/view/${screenClass}.kt" />

        <#else>

            <instantiate from="root/src/app_package/classes_kotlin/FragmentMoxy.kt.kt.ftl"
                to="${escapeXmlAttribute(topOut)}/${screenModuleName}/src/main/java/${slashedPackageName(screenPackageName)}/view/${screenClass}.kt" />

        </#if>

        <instantiate from="root/src/app_package/classes_kotlin/ViewMoxy.kt.ftl"
            to="${escapeXmlAttribute(topOut)}/${screenModuleName}/src/main/java/${slashedPackageName(screenPackageName)}/view/${screenViewClass}.kt" />

        <instantiate from="root/src/app_package/classes_kotlin/PresenterMoxy.kt.ftl"
            to="${escapeXmlAttribute(topOut)}/${screenModuleName}/src/main/java/${slashedPackageName(screenPackageName)}/presenter/${screenPresenterClass}.kt" />

    <#else>

        <#if screenType == 'activity'>

            <instantiate from="root/src/app_package/classes_kotlin/Activity.kt.ftl"
                to="${escapeXmlAttribute(topOut)}/${screenModuleName}/src/main/java/${slashedPackageName(screenPackageName)}/view/${screenClass}.kt" />

        <#else>

            <instantiate from="root/src/app_package/classes_kotlin/Fragment.kt.kt.ftl"
                to="${escapeXmlAttribute(topOut)}/${screenModuleName}/src/main/java/${slashedPackageName(screenPackageName)}/view/${screenClass}.kt" />

        </#if>

        <instantiate from="root/src/app_package/classes_kotlin/View.kt.ftl"
            to="${escapeXmlAttribute(topOut)}/${screenModuleName}/src/main/java/${slashedPackageName(screenPackageName)}/view/${screenViewClass}.kt" />

        <instantiate from=root/"src/app_package/classes_kotlin/Presenter.kt.ftl"
            to="${escapeXmlAttribute(topOut)}/${screenModuleName}/src/main/java/${slashedPackageName(screenPackageName)}/presenter/${screenPresenterClass}.kt" />

    </#if>

</recipe>
