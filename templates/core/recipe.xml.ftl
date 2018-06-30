<?xml version="1.0"?>

    <merge from="root/settings.gradle.ftl"
        to="${escapeXmlAttribute(topOut)}/settings.gradle" />

    <instantiate from="root/build.gradle.ftl"
        to="${escapeXmlAttribute(projectOut)}/build.gradle" />

    <#if generateKotlin>
    	<#include "kotlin_files_recipe.xml.ftl" />
    <#else>
    	<#include "java_files_recipe.xml.ftl" />
    </#if>
</recipe>
