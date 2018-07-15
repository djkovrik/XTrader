import Versions.ESPRESSO_VERSION
import Versions.EXPEKT_VERSION
import Versions.JUNIT_PLATFORM_ENGINE_VERSION
import Versions.JUNIT_PLATFORM_RUNNER_VERSION
import Versions.JUNIT_VERSION
import Versions.KAKAO_VERSION
import Versions.SPEK_VERSION
import Versions.TEST_RULES_VERSION
import Versions.TEST_RUNNER_VERSION

object TestingDependencies {
  const val androidTestRunner = "androidx.test:runner:$TEST_RUNNER_VERSION"
  const val androidTestRules = "androidx.test:rules:$TEST_RULES_VERSION"
  const val espresso = "androidx.test.espresso:espresso-core::$ESPRESSO_VERSION"
  const val espressoIntents = "androidx.test.espresso:espresso-intents:$ESPRESSO_VERSION"
  const val espressoIdling = "androidx.test.espresso:espresso-idling-resource:$ESPRESSO_VERSION"

  const val junit = "junit:junit:$JUNIT_VERSION"
  const val junitPlatform = "org.junit.platform:junit-platform-engine:$JUNIT_PLATFORM_ENGINE_VERSION"
  const val junitPlatformRunner = "org.junit.platform:junit-platform-runner:$JUNIT_PLATFORM_RUNNER_VERSION"
  const val spekApi = "org.jetbrains.spek:spek-api:$SPEK_VERSION"
  const val spekEngine = "org.jetbrains.spek:spek-junit-platform-engine:$SPEK_VERSION"
  const val expekt = "com.winterbe:expekt:$EXPEKT_VERSION"
  const val kakao = "com.agoda.kakao:kakao:$KAKAO_VERSION"
}
