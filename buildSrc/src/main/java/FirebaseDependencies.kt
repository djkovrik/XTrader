import Versions.FIREBASE_ANALYTICS_VERSION
import Versions.FIREBASE_CLOUD_MESSAGING_VERSION
import Versions.FIREBASE_CORE_VERSION
import Versions.FIREBASE_CRASHLYTICS_VERSION
import Versions.FIREBASE_FIRESTORE_VERSION
import Versions.FIREBASE_PERFOMANCE_MONITOR_VERSION

object FirebaseDependencies {
    const val core = "com.google.firebase:firebase-core:$FIREBASE_CORE_VERSION"
    const val analytics = "com.google.firebase:firebase-analytics:$FIREBASE_ANALYTICS_VERSION"
    const val firestore = "com.google.firebase:firebase-firestore:$FIREBASE_FIRESTORE_VERSION"
    const val messaging = "com.google.firebase:firebase-messaging:$FIREBASE_CLOUD_MESSAGING_VERSION"
    const val crashlytics = "com.crashlytics.sdk.android:crashlytics:$FIREBASE_CRASHLYTICS_VERSION"
    const val performance = "com.google.firebase:firebase-perf:$FIREBASE_PERFOMANCE_MONITOR_VERSION"
}
