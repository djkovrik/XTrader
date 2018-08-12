<recipe>

    <instantiate from="root/src/app_package/classes_java/App.java.ftl"
        to="${escapeXmlAttribute(srcOut)}/${applicationClass}.java" />

    <instantiate from="root/src/app_package/classes_java/AppComponent.java.ftl"
        to="${escapeXmlAttribute(srcOut)}/di/${applicationInterface}Component.java" />

</recipe>
