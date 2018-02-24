import Versions.KOTLIN_COROUTINES_VERSION
import Versions.KOTLIN_VERSION
import Versions.TOOTHPICK_VERSION

object DomainDependencies {
  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$KOTLIN_VERSION"
  const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$KOTLIN_COROUTINES_VERSION"
  const val toothpick = "com.github.stephanenicolas.toothpick:toothpick-runtime:$TOOTHPICK_VERSION"
  const val toothpickCompiler = "com.github.stephanenicolas.toothpick:toothpick-compiler:$TOOTHPICK_VERSION"
}
