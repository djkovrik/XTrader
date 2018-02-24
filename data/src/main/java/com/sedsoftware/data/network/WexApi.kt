package com.sedsoftware.data.network

import com.sedsoftware.data.entity.WexCurrencyPairsList
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

interface WexApi {

  @GET("info")
  fun loadCurrencyPairsList(): Deferred<WexCurrencyPairsList>
}
