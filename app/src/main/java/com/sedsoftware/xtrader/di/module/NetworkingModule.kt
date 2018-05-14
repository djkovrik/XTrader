package com.sedsoftware.xtrader.di.module

import com.sedsoftware.data.network.WexApi
import com.sedsoftware.xtrader.di.provider.ApiProvider
import com.sedsoftware.xtrader.di.provider.MoshiProvider
import com.sedsoftware.xtrader.di.provider.OkHttpClientProvider
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import toothpick.config.Module

class NetworkingModule : Module() {
  init {
    bind(Moshi::class.java).toProvider(MoshiProvider::class.java).providesSingletonInScope()
    bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java)
      .providesSingletonInScope()
    bind(WexApi::class.java).toProvider(ApiProvider::class.java).providesSingletonInScope()
  }
}
