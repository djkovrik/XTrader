<recipe>
    <mkdir at="${escapeXmlAttribute(topOut)}/${screenModuleName}" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${screenModuleName}/src" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${screenModuleName}/src/main" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${screenModuleName}/src/main/java" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${screenModuleName}/src/main/java/${slashedPackageName(screenPackageName)}/di" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${screenModuleName}/src/main/java/${slashedPackageName(screenPackageName)}/view" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${screenModuleName}/src/main/java/${slashedPackageName(screenPackageName)}/presenter" />
</recipe>
