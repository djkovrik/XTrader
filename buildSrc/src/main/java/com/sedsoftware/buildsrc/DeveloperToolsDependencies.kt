package com.sedsoftware.buildsrc

import com.sedsoftware.buildsrc.Versions.LEAK_CANARY_VERSION
import com.sedsoftware.buildsrc.Versions.TIMBER_VERSION

object DeveloperToolsDependencies {
  const val timber = "com.jakewharton.timber:timber:$TIMBER_VERSION"
  const val leakCanary = "com.squareup.leakcanary:leakcanary-android:$LEAK_CANARY_VERSION"
  const val leakCanaryNoOp = "com.squareup.leakcanary:leakcanary-android-no-op:$LEAK_CANARY_VERSION"
}
