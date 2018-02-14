import Versions.EXPEKT_VERSION
import Versions.JUNIT_VERSION
import Versions.MOCKITO_KOTLIN_VERSION
import Versions.MOCKITO_VERSION

object DeviceTestDependencies {
  const val junit = "junit:junit:$JUNIT_VERSION"
  const val mockito = "org.mockito:mockito-core:$MOCKITO_VERSION"
  const val mockitoKotlin = "com.nhaarman:mockito-kotlin-kt1.1:$MOCKITO_KOTLIN_VERSION"
  const val expekt = "com.winterbe:expekt:$EXPEKT_VERSION"
}
