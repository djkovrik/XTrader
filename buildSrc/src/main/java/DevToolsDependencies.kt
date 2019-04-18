import Versions.CHUCK_VERSION
import Versions.LEAK_CANARY_VERSION
import Versions.TIMBER_VERSION

object DevToolsDependencies {
    const val timber = "com.jakewharton.timber:timber:$TIMBER_VERSION"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:$LEAK_CANARY_VERSION"
    const val leakCanaryNoOp = "com.squareup.leakcanary:leakcanary-android-no-op:$LEAK_CANARY_VERSION"
    const val chuk = "com.readystatesoftware.chuck:library:$CHUCK_VERSION"
    const val chukNoOp = "com.readystatesoftware.chuck:library-no-op:$CHUCK_VERSION"
}
