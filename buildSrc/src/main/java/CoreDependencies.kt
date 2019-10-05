import Versions.ADAPTER_DELEGATES_VERSION
import Versions.CICERONE_VERSION
import Versions.DAGGER_VERSION
import Versions.INJECTION_HOLDER_VERSION
import Versions.MOSHI_VERSION
import Versions.OKHTTP_VERSION
import Versions.RETROFIT_VERSION
import Versions.THREE_TEN_ABP_VERSION

object CoreDependencies {
    const val cicerone = "ru.terrakok.cicerone:cicerone:$CICERONE_VERSION"
    const val threetenabp = "com.jakewharton.threetenabp:threetenabp:$THREE_TEN_ABP_VERSION"
    const val adapterDelegates = "com.hannesdorfmann:adapterdelegates4:$ADAPTER_DELEGATES_VERSION"
    const val dagger = "com.google.dagger:dagger:$DAGGER_VERSION"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:$DAGGER_VERSION"
    const val injectionHolder = "com.github.chernovdmitriy.injectionholder:androidx:$INJECTION_HOLDER_VERSION"
    const val retrofit = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
    const val moshi = "com.squareup.moshi:moshi:$MOSHI_VERSION"
    const val moshiAdapters = "com.squareup.moshi:moshi-adapters:$MOSHI_VERSION"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$MOSHI_VERSION"
    const val okhttp = "com.squareup.okhttp3:okhttp:$OKHTTP_VERSION"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$OKHTTP_VERSION"
    const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:$RETROFIT_VERSION"
}
