<?xml version="1.0"?>

    <#if generateKotlin>
    	<#include "kotlin_files_recipe.xml.ftl" />
    <#else>
    	<#include "java_files_recipe.xml.ftl" />
    </#if>
</recipe>
