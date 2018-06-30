<recipe>

    <instantiate from="root/src/app_package/classes_kotlin/BaseActivity.kt.ftl"
        to="${escapeXmlAttribute(srcOut)}/base/BaseActivity.kt" />

    <instantiate from="root/src/app_package/classes_kotlin/ApplicationProvider.kt.ftl"
        to="${escapeXmlAttribute(srcOut)}/base/BaseFragment.kt" />

</recipe>
