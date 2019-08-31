import Versions.ANDROID_DEBUG_DATABASE_VERSION
import Versions.LEAK_CANARY_VERSION
import Versions.TIMBER_VERSION

object DevToolsDependencies {
    const val timber = "com.jakewharton.timber:timber:$TIMBER_VERSION"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:$LEAK_CANARY_VERSION"
    const val androidDebugDatabase = "com.amitshekhar.android:debug-db:$ANDROID_DEBUG_DATABASE_VERSION"
}
