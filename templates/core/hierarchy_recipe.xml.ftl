<recipe>
    <merge from="root/src/settings.gradle.ftl"
        to="${escapeXmlAttribute(topOut)}/settings.gradle" />

    <instantiate from="root/src/build.gradle.ftl"
        to="${escapeXmlAttribute(projectOut)}/build.gradle" />

    <instantiate from="root/src/AndroidManifest.xml.ftl"
        to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />

    <instantiate from="root/src/res/values/strings.xml.ftl"
        to="${escapeXmlAttribute(resOut)}/values/strings.xml" />

    <copy from="root://gradle-projects/common/gitignore"
        to="${escapeXmlAttribute(projectOut)}/.gitignore" />

    <#include "root://gradle-projects/common/proguard_recipe.xml.ftl"/>

    <mkdir at="${escapeXmlAttribute(srcOut)}/di" />
    <mkdir at="${escapeXmlAttribute(srcOut)}/di/provider" />
    <mkdir at="${escapeXmlAttribute(srcOut)}/di/scope" />
</recipe>
