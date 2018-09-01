<recipe>
    <mkdir at="${escapeXmlAttribute(topOut)}/${coreUiModuleName}" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${coreUiModuleName}/src" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${coreUiModuleName}/src/main" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${coreUiModuleName}/src/main/java" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${coreUiModuleName}/src/main/java/${slashedPackageName(coreUiPackageName)}/base" />
    <mkdir at="${escapeXmlAttribute(topOut)}/${coreUiModuleName}/src/main/java/${slashedPackageName(coreUiPackageName)}/custom" />
</recipe>
