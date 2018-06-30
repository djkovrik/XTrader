<recipe>

    <instantiate from="root/src/app_package/classes_java/BaseActivity.java.ftl"
        to="${escapeXmlAttribute(srcOut)}/base/BaseActivity.java" />

    <instantiate from="root/src/app_package/classes_java/ApplicationProvider.java.ftl"
        to="${escapeXmlAttribute(srcOut)}/base/BaseFragment.java" />

</recipe>
