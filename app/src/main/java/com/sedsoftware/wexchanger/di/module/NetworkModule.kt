package com.sedsoftware.wexchanger.di.module

import com.sedsoftware.data.network.WexApi
import com.sedsoftware.wexchanger.di.provider.ApiProvider
import com.sedsoftware.wexchanger.di.provider.MoshiProvider
import com.sedsoftware.wexchanger.di.provider.OkHttpClientProvider
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import toothpick.config.Module

class NetworkModule : Module() {
  init {
    // Networking
    bind(Moshi::class.java).toProvider(MoshiProvider::class.java).singletonInScope()
    bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java).singletonInScope()
    bind(WexApi::class.java).toProvider(ApiProvider::class.java).singletonInScope()
  }
}
