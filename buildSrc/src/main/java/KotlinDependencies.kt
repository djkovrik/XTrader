import Versions.KOTLIN_COROUTINES_VERSION
import Versions.KOTLIN_VERSION

object KotlinDependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$KOTLIN_VERSION"
    const val kotlinReflection = "org.jetbrains.kotlin:kotlin-reflect:$KOTLIN_VERSION"
    const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$KOTLIN_COROUTINES_VERSION"
    const val kotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$KOTLIN_COROUTINES_VERSION"
}
