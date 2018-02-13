package com.sedsoftware.buildsrc

import com.sedsoftware.buildsrc.Versions.EXPEKT_VERSION
import com.sedsoftware.buildsrc.Versions.JUNIT_VERSION
import com.sedsoftware.buildsrc.Versions.MOCKITO_KOTLIN_VERSION
import com.sedsoftware.buildsrc.Versions.MOCKITO_VERSION

object DeviceTestDependencies {
  const val junit = "junit:junit:$JUNIT_VERSION"
  const val mockito = "org.mockito:mockito-core:$MOCKITO_VERSION"
  const val mockitoKotlin = "com.nhaarman:mockito-kotlin-kt1.1:$MOCKITO_KOTLIN_VERSION"
  const val expekt = "com.winterbe:expekt:$EXPEKT_VERSION"
}
