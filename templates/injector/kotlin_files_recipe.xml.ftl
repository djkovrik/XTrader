<?xml version="1.0"?>
<recipe>

    <instantiate from="src/app_package/classes_kotlin/App.kt.ftl"
        to="${escapeXmlAttribute(srcOut)}/${applicationClass}.kt" />

    <instantiate from="src/app_package/classes_kotlin/AppComponent.kt.ftl"
        to="${escapeXmlAttribute(srcOut)}/di/${applicationInterface}Component.kt" />

</recipe>
