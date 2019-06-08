import Versions.APPCOMPAT_VERSION
import Versions.CONSTRAINT_LAYOUT_VERSION
import Versions.KTX_VERSION
import Versions.LEGACY_SUPPORT_UTILS_VERSION
import Versions.LIFECYCLE_VERSION
import Versions.MATERIAL_COMPONENTS_VERSION
import Versions.NAVIGATION_VERSION
import Versions.ROOM_VERSION

object JetPackDependencies {
    const val appCompat = "androidx.appcompat:appcompat:$APPCOMPAT_VERSION"
    const val materialComponents = "com.google.android.material:material:$MATERIAL_COMPONENTS_VERSION"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:$CONSTRAINT_LAYOUT_VERSION"

    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:$LIFECYCLE_VERSION"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$LIFECYCLE_VERSION"
    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$LIFECYCLE_VERSION"
    const val lifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$LIFECYCLE_VERSION"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:$LIFECYCLE_VERSION"

    const val room = "androidx.room:room-runtime:$ROOM_VERSION"
    const val roomKtx = "androidx.room:room-ktx:$ROOM_VERSION"
    const val roomCompiler = "androidx.room:room-compiler:$ROOM_VERSION"

    const val navigationUi = "androidx.navigation:navigation-ui-ktx:$NAVIGATION_VERSION"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$NAVIGATION_VERSION"
    const val navigationRuntime = "androidx.navigation:navigation-runtime-ktx:$NAVIGATION_VERSION"

    const val KTX = "androidx.core:core-ktx:$KTX_VERSION"

    const val legacyUtils = "androidx.legacy:legacy-support-core-utils:$LEGACY_SUPPORT_UTILS_VERSION"
}
