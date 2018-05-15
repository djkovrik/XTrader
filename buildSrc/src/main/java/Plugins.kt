
import Versions.DETEKT_VERSION
import Versions.GRADLE_ANDROID_VERSION
import Versions.GRADLE_VERSIONING_VERSION
import Versions.GRADLE_VERSIONS_VERSION
import Versions.KOTLIN_VERSION

object Plugins {
  const val ANDROID_GRADLE = "com.android.tools.build:gradle:$GRADLE_ANDROID_VERSION"
  const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN_VERSION"
  const val DETEKT = "gradle.plugin.io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$DETEKT_VERSION"
  const val GRADLE_VERSIONS = "com.github.ben-manes:gradle-versions-plugin:$GRADLE_VERSIONS_VERSION"
  const val GRADLE_VERSIONING = "gradle.plugin.com.gladed.gradle.androidgitversion:gradle-android-git-version:$GRADLE_VERSIONING_VERSION"
}
