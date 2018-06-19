<?xml version="1.0"?>
<recipe>
    <#include "hierarchy_recipe.xml.ftl" />

    <#if generateKotlin>
    	<#include "kotlin_files_recipe.xml.ftl" />
    <#else>
    	<#include "java_files_recipe.xml.ftl" />
    </#if>
</recipe>
