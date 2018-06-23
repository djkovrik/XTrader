<?xml version="1.0"?>
<recipe>

    <instantiate from="src/app_package/classes_java/App.java.ftl"
        to="${escapeXmlAttribute(srcOut)}/${applicationClass}.java" />

    <instantiate from="src/app_package/classes_java/AppComponent.java.ftl"
        to="${escapeXmlAttribute(srcOut)}/di/${applicationInterface}Component.java" />

</recipe>
