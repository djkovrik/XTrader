import Versions.APPCOMPAT_VERSION
import Versions.CARDVIEW_VERSION
import Versions.CONSTRAINT_LAYOUT_VERSION
import Versions.KTX_VERSION
import Versions.LIFECYCLE_VERSION
import Versions.MATERIAL_COMPONENTS_VERSION
import Versions.NAVIGATION_VERSION
import Versions.PAGING_VERSION
import Versions.PREFERENCE_VERSION
import Versions.RECYCLER_VIEW_VERSION
import Versions.ROOM_VERSION
import Versions.WORKMANAGER_VERSION

object JetPackDependencies {
  const val appVompat = "androidx.appcompat:appcompat:$APPCOMPAT_VERSION"
  const val cardView = "androidx.cardview:cardview:$CARDVIEW_VERSION"
  const val materialComponents = "com.google.android.material:material:$MATERIAL_COMPONENTS_VERSION"
  const val recyclerView = "androidx.recyclerview:recyclerview:$RECYCLER_VIEW_VERSION"
  const val preference = "androidx.preference:preference:$PREFERENCE_VERSION"
  const val recyclerViewSelection = "androidx.recyclerview:recyclerview-selection:$RECYCLER_VIEW_VERSION"
  const val constraintLayout = "androidx.constraintlayout:constraintlayout:$CONSTRAINT_LAYOUT_VERSION"

  const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:$LIFECYCLE_VERSION"
  const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:$LIFECYCLE_VERSION"

  const val room = "androidx.room:room-runtime:$ROOM_VERSION"
  const val roomCompiler = "androidx.room:room-compiler:$ROOM_VERSION"

  const val paging = "androidx.paging:paging-runtime:$PAGING_VERSION"

  const val navigation = "android.arch.navigation:navigation-fragment-ktx:$NAVIGATION_VERSION"
  const val navigationUi = "android.arch.navigation:navigation-ui-ktx:$NAVIGATION_VERSION"

  const val workManager = "android.arch.work:work-runtime:$WORKMANAGER_VERSION"
  const val workManagerFirebase = "android.arch.work:work-firebase:$WORKMANAGER_VERSION"

  const val liveDataTestHelpers = "androidx.arch.core:core-testing:$LIFECYCLE_VERSION"
  const val roomTestHelpers = "androidx.room:room-testing:$ROOM_VERSION"
  const val navigationTestHelpers = "android.arch.navigation:navigation-testing-ktx:$NAVIGATION_VERSION"
  const val workManagerTestHelpers = "android.arch.work:work-testing:$NAVIGATION_VERSION"

  const val KTX = "androidx.core:core-ktx:$KTX_VERSION"
}
