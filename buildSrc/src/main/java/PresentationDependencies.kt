import Versions.ANKO_VERSION
import Versions.CICERONE_VERSION
import Versions.COMMUNITY_MATERIAL_ICONS_VERSION
import Versions.CONSTRAINT_LAYOUT_VERSION
import Versions.GLIDE_VERSION
import Versions.ICONICS_VERSION
import Versions.KOTLIN_COROUTINES_VERSION
import Versions.KOTLIN_VERSION
import Versions.MATERIAL_DIALOGS_VERSION
import Versions.MATERIAL_VALUES_VERSION
import Versions.MOSHI_VERSION
import Versions.MOXY_VERSION
import Versions.OKHTTP_VERSION
import Versions.RETROFIT_COROUTINES_ADAPTER_VERSION
import Versions.RETROFIT_VERSION
import Versions.SUPPORT_LIBRARY_VERSION
import Versions.TOOTHPICK_VERSION

object PresentationDependencies {
  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$KOTLIN_VERSION"
  const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$KOTLIN_COROUTINES_VERSION"
  const val kotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$KOTLIN_COROUTINES_VERSION"

  const val appCompat = "com.android.support:appcompat-v7:$SUPPORT_LIBRARY_VERSION"
  const val cardView = "com.android.support:cardview-v7:$SUPPORT_LIBRARY_VERSION"
  const val design = "com.android.support:design:$SUPPORT_LIBRARY_VERSION"
  const val recyclerView = "com.android.support:recyclerview-v7:$SUPPORT_LIBRARY_VERSION"
  const val constraintLayout = "com.android.support.constraint:constraint-layout:$CONSTRAINT_LAYOUT_VERSION"

  const val toothpick = "com.github.stephanenicolas.toothpick:toothpick-runtime:$TOOTHPICK_VERSION"
  const val toothpickAndroid = "com.github.stephanenicolas.toothpick:smoothie:$TOOTHPICK_VERSION"
  const val toothpickCompiler = "com.github.stephanenicolas.toothpick:toothpick-compiler:$TOOTHPICK_VERSION"
  const val moxy = "com.arello-mobile:moxy:$MOXY_VERSION"
  const val moxyCompiler = "com.arello-mobile:moxy-compiler:$MOXY_VERSION"
  const val moxyAndroid = "com.arello-mobile:moxy-android:$MOXY_VERSION"
  const val moxyAppCompat = "com.arello-mobile:moxy-app-compat:$MOXY_VERSION"
  const val cicerone = "ru.terrakok.cicerone:cicerone:$CICERONE_VERSION"
  const val anko = "org.jetbrains.anko:anko-commons:$ANKO_VERSION"
  const val ankoDesign = "org.jetbrains.anko:anko-design:$ANKO_VERSION"

  const val moshi = "com.squareup.moshi:moshi:$MOSHI_VERSION"
  const val retrofit = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
  const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:$RETROFIT_VERSION"
  const val retrofitScalarsConverter = "com.squareup.retrofit2:converter-scalars:$RETROFIT_VERSION"
  const val retrofitCoroutines =
    "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-experimental-adapter:$RETROFIT_COROUTINES_ADAPTER_VERSION"
  const val okhttp = "com.squareup.okhttp3:okhttp:$OKHTTP_VERSION"
  const val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:$OKHTTP_VERSION"
  const val glide = "com.github.bumptech.glide:glide:$GLIDE_VERSION"
  const val glideCompiler = "com.github.bumptech.glide:compiler:$GLIDE_VERSION"

  const val materialValues = "blue.aodev:material-values:$MATERIAL_VALUES_VERSION"
  const val iconicsCore = "com.mikepenz:iconics-core:$ICONICS_VERSION"
  const val iconicsView = "com.mikepenz:iconics-views:$ICONICS_VERSION"
  const val materialTypeface = "com.mikepenz:community-material-typeface:$COMMUNITY_MATERIAL_ICONS_VERSION"
  const val materialDialogs = "com.afollestad.material-dialogs:core:$MATERIAL_DIALOGS_VERSION"
  const val materialDialogsCommons = "com.afollestad.material-dialogs:commons:$MATERIAL_DIALOGS_VERSION"
}
