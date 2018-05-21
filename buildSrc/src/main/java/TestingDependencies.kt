import Versions.ESPRESSO_VERSION
import Versions.EXPEKT_VERSION
import Versions.JUNIT_VERSION
import Versions.KAKAO_VERSION
import Versions.MOCKITO_KOTLIN_VERSION
import Versions.MOCKITO_VERSION
import Versions.SPEK_VERSION
import Versions.TEST_RUNNER_VERSION

object TestingDependencies {
  const val androidTestRunner = "com.android.support.test:runner:$TEST_RUNNER_VERSION"
  const val androidTestRules = "com.android.support.test:rules:$TEST_RUNNER_VERSION"
  const val espresso = "com.android.support.test.espresso:espresso-core:$ESPRESSO_VERSION"
  const val espressoIntents = "com.android.support.test.espresso:espresso-intents:$ESPRESSO_VERSION"
  const val espressoIdling = "com.android.support.test.espresso.idling:idling-concurrent:$ESPRESSO_VERSION"

  const val junit = "junit:junit:$JUNIT_VERSION"
  const val mockito = "org.mockito:mockito-core:$MOCKITO_VERSION"
  const val mockitoKotlin = "com.nhaarman:mockito-kotlin-kt1.1:$MOCKITO_KOTLIN_VERSION"
  const val mockitoAndroid = "org.mockito:mockito-android:$MOCKITO_VERSION"
  const val spekApi = "org.jetbrains.spek:spek-api:$SPEK_VERSION"
  const val spekEngine = "org.jetbrains.spek:spek-junit-platform-engine:$SPEK_VERSION"
  const val expekt = "com.winterbe:expekt:$EXPEKT_VERSION"
  const val kakao = "com.agoda.kakao:kakao:$KAKAO_VERSION"
}
