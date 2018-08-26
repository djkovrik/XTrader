<recipe>
    <mkdir at="${escapeXmlAttribute(topOut)}/${featureModuleName}" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${featureModuleName}/src" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${featureModuleName}/src/main" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${featureModuleName}/src/main/java" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${featureModuleName}/src/main/java/${slashedPackageName(featurePackageName)}/di" />
</recipe>
