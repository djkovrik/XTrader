<?xml version="1.0"?>
<recipe>
    <#include "hierarchy_recipe.xml.ftl" />

    <merge from="root/settings.gradle.ftl"
        to="${escapeXmlAttribute(topOut)}/settings.gradle" />

    <instantiate from="root/build.gradle.ftl"
        to="${escapeXmlAttribute(topOut)}/${providerModuleName}/build.gradle" />

    <instantiate from="root/src/AndroidManifest.xml.ftl"
        to="${escapeXmlAttribute(topOut)}/${providerModuleName}/src/main/AndroidManifest.xml" />

    <instantiate from="root/src/res/values/strings.xml.ftl"
        to="${escapeXmlAttribute(topOut)}/${providerModuleName}/src/main/res/values/strings.xml" />

    <copy from="root://gradle-projects/common/gitignore"
        to="${escapeXmlAttribute(topOut)}/${providerModuleName}/.gitignore" />

    <merge from="root/merge_build.gradle.ftl"
        to="${escapeXmlAttribute(topOut)}/app/build.gradle" />

    <#include "root://gradle-projects/common/proguard_recipe.xml.ftl"/>

    <#include "kotlin_files_recipe.xml.ftl" />

</recipe>
