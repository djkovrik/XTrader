<recipe>
    <mkdir at="${escapeXmlAttribute(topOut)}/${coreModuleName}" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${coreModuleName}/src" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${coreModuleName}/src/main" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${coreModuleName}/src/main/java" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${coreModuleName}/src/main/java/${slashedPackageName(corePackageName)}/di" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${coreModuleName}/src/main/java/${slashedPackageName(corePackageName)}/di/provider" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${coreModuleName}/src/main/java/${slashedPackageName(corePackageName)}/di/scope" />

</recipe>
