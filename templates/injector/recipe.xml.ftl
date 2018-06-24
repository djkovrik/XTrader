<?xml version="1.0"?>
<recipe>
    <#include "hierarchy_recipe.xml.ftl" />

    <merge from="root/src/app_package/common/Manifest.xml.ftl"
        to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />

    <#if generateKotlin>
    	<#include "kotlin_files_recipe.xml.ftl" />
    <#else>
    	<#include "java_files_recipe.xml.ftl" />
    </#if>
</recipe>
