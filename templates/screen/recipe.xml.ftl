<?xml version="1.0"?>
<recipe>

    <#include "hierarchy_recipe.xml.ftl" />

    <merge from="root/settings.gradle.ftl"
        to="${escapeXmlAttribute(topOut)}/settings.gradle" />

    <instantiate from="root/build.gradle.ftl"
        to="${escapeXmlAttribute(topOut)}/${screenModuleName}/build.gradle" />

    <merge from="root/merge_build.gradle.ftl"
        to="${escapeXmlAttribute(topOut)}/app/build.gradle" />

    <instantiate from="root/src/res/values/strings.xml.ftl"
        to="${escapeXmlAttribute(topOut)}/${screenModuleName}/src/main/res/values/strings.xml" />

    <instantiate from="root/src/res/layout/view_layout.xml.ftl"
        to="${escapeXmlAttribute(topOut)}/${screenModuleName}/src/main/res/layout/${screenLayoutName}.xml" />

    <merge from="root/src/app_package/AndroidManifest.xml.ftl"
        to="${escapeXmlAttribute(topOut)}/${screenModuleName}/src/main/AndroidManifest.xml" />

    <copy from="root://gradle-projects/common/gitignore"
        to="${escapeXmlAttribute(topOut)}/${screenModuleName}/.gitignore" />

    <#include "kotlin_files_recipe.xml.ftl" />

</recipe>
