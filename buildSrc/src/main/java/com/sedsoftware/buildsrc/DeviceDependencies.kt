package com.sedsoftware.buildsrc

import com.sedsoftware.buildsrc.Versions.JAVAX_INJECT_VERSION
import com.sedsoftware.buildsrc.Versions.KOTLIN_COROUTINES_VERSION
import com.sedsoftware.buildsrc.Versions.KOTLIN_VERSION
import com.sedsoftware.buildsrc.Versions.SUPPORT_LIBRARY_VERSION

object DeviceDependencies {
  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$KOTLIN_VERSION"
  const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$KOTLIN_COROUTINES_VERSION"
  const val kotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$KOTLIN_COROUTINES_VERSION"
  const val appCompat = "com.android.support:appcompat-v7:$SUPPORT_LIBRARY_VERSION"
  const val javax = "javax.inject:javax.inject:$JAVAX_INJECT_VERSION"
}
