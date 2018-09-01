<?xml version="1.0"?>
<recipe>

    <#include "hierarchy_recipe.xml.ftl" />

    <merge from="root/settings.gradle.ftl"
        to="${escapeXmlAttribute(topOut)}/settings.gradle" />

    <instantiate from="root/build.gradle.ftl"
        to="${escapeXmlAttribute(topOut)}/${coreUiModuleName}/build.gradle" />

    <merge from="root/merge_build.gradle.ftl"
        to="${escapeXmlAttribute(topOut)}/app/build.gradle" />

    <instantiate from="root/src/AndroidManifest.xml.ftl"
        to="${escapeXmlAttribute(topOut)}/${coreUiModuleName}/src/main/AndroidManifest.xml" />

    <instantiate from="root/src/res/values/strings.xml.ftl"
        to="${escapeXmlAttribute(topOut)}/${coreUiModuleName}/src/main/res/values/strings.xml" />

    <copy from="root://gradle-projects/common/gitignore"
        to="${escapeXmlAttribute(topOut)}/${coreUiModuleName}/.gitignore" />

    <#include "kotlin_files_recipe.xml.ftl" />

</recipe>
