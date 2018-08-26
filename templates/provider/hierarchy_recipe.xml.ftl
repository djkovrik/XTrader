<recipe>
    <mkdir at="${escapeXmlAttribute(topOut)}/${providerModuleName}" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${providerModuleName}/src" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${providerModuleName}/src/main" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${providerModuleName}/src/main/java" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${providerModuleName}/src/main/java/${slashedPackageName(providerPackageName)}/di" />
</recipe>
