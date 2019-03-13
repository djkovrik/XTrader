import Versions.ESPRESSO_VERSION
import Versions.EXPEKT_VERSION
import Versions.FUEL_VERSION
import Versions.JUNIT5_RUNNER_VERSION
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

    const val spekDsl = "org.spekframework.spek2:spek-dsl-jvm:$SPEK_VERSION"
    const val spekRunner = "org.spekframework.spek2:spek-runner-junit5:$SPEK_VERSION"
    const val junit5Instrumentation = "de.mannodermaus.junit5:android-instrumentation-test-runner:$JUNIT5_RUNNER_VERSION"

    const val expekt = "com.winterbe:expekt:$EXPEKT_VERSION"
    const val kakao = "com.agoda.kakao:kakao:$KAKAO_VERSION"

    const val fuel = "com.github.kittinunf.fuel:fuel:$FUEL_VERSION"
    const val fuelCoroutines = "com.github.kittinunf.fuel:fuel-coroutines:$FUEL_VERSION"
    const val fuelCoroutinesMoshi = "com.github.kittinunf.fuel:fuel-moshi:$FUEL_VERSION"
}
