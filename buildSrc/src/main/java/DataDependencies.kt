import Versions.JAVAX_INJECT_VERSION
import Versions.KOTLIN_COROUTINES_VERSION
import Versions.KOTLIN_VERSION
import Versions.RETROFIT_VERSION

object DataDependencies {
  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$KOTLIN_VERSION"
  const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$KOTLIN_COROUTINES_VERSION"
  const val kotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$KOTLIN_COROUTINES_VERSION"
  const val javaxInject = "javax.inject:javax.inject:$JAVAX_INJECT_VERSION"
  const val retrofit = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
}
