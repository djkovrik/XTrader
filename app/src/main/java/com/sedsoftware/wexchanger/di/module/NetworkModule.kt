package com.sedsoftware.wexchanger.di.module

import com.sedsoftware.data.mapper.WexPairsMapper
import com.sedsoftware.data.network.WexApi
import com.sedsoftware.data.repository.WexCurrencyPairsRepository
import com.sedsoftware.domain.interactor.GetCurrencyPairsUseCase
import com.sedsoftware.domain.repository.CurrencyPairsRepository
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

    // Currency pairs
    bind(CurrencyPairsRepository::class.java).to(WexCurrencyPairsRepository::class.java).singletonInScope()
    bind(WexPairsMapper::class.java).singletonInScope()
    bind(GetCurrencyPairsUseCase::class.java).singletonInScope()
  }
}
