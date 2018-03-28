
import Versions.ESPRESSO_VERSION
import Versions.JUNIT_VERSION
import Versions.MOCKITO_KOTLIN_VERSION
import Versions.MOCKITO_VERSION

object PresentationTestDependencies {
  const val junit = "junit:junit:$JUNIT_VERSION"
  const val mockito = "org.mockito:mockito-core:$MOCKITO_VERSION"
  const val mockitoKotlin = "com.nhaarman:mockito-kotlin-kt1.1:$MOCKITO_KOTLIN_VERSION"
  const val mockitoAndroid = "org.mockito:mockito-android:$MOCKITO_VERSION"
  const val espresso = "com.android.support.test.espresso:espresso-core:$ESPRESSO_VERSION"
}
