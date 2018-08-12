<?xml version="1.0"?>
<recipe>
    <#include "hierarchy_recipe.xml.ftl" />

    <merge from="root/src/app_package/common/Manifest.xml.ftl"
        to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />

    <merge from="root/build.gradle.ftl"
        to="${escapeXmlAttribute(topOut)}/app/build.gradle" />

    <#include "kotlin_files_recipe.xml.ftl" />

</recipe>
