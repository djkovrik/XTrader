import Versions.ANDROID_JUNIT5_VERSION
import Versions.DETEKT_VERSION
import Versions.DEV_METRICS_VERSION
import Versions.GRADLE_ANDROID_VERSION
import Versions.GRADLE_SCAN_VERSION
import Versions.GRADLE_VERSIONING_VERSION
import Versions.GRADLE_VERSIONS_VERSION
import Versions.KOTLIN_VERSION
import Versions.SAFE_ARGS_VERSION

object Plugins {
    const val ANDROID_GRADLE =
        "com.android.tools.build:gradle:$GRADLE_ANDROID_VERSION"
    const val KOTLIN_GRADLE_PLUGIN =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN_VERSION"
    const val DETEKT =
        "gradle.plugin.io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$DETEKT_VERSION"
    const val GRADLE_VERSIONS =
        "com.github.ben-manes:gradle-versions-plugin:$GRADLE_VERSIONS_VERSION"
    const val GRADLE_VERSIONING =
        "gradle.plugin.com.gladed.gradle.androidgitversion:gradle-android-git-version:$GRADLE_VERSIONING_VERSION"
    const val DEVICE_METRICS =
        "com.frogermcs.androiddevmetrics:androiddevmetrics-plugin:$DEV_METRICS_VERSION"
    const val SAFE_ARGS =
        "android.arch.navigation:navigation-safe-args-gradle-plugin:$SAFE_ARGS_VERSION"
    const val JUNIT5 =
        "de.mannodermaus.gradle.plugins:android-junit5:$ANDROID_JUNIT5_VERSION"
    const val SCAN =
        "com.gradle:build-scan-plugin:$GRADLE_SCAN_VERSION"
}
