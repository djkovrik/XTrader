package com.sedsoftware.exchange.binance.fakedata

import com.sedsoftware.exchange.binance.network.model.dto.PairsInfoDto
import com.sedsoftware.exchange.binance.network.model.params.RateLimit
import com.sedsoftware.exchange.binance.network.model.params.RateLimitInterval
import com.sedsoftware.exchange.binance.network.model.params.RateLimitType
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.Date

class FakeSymbolsInfoData {

    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .build()
    }

    private val jsonAdapter: JsonAdapter<PairsInfoDto> by lazy {
        return@lazy moshi.adapter(PairsInfoDto::class.java)
    }

    fun getRawParsedDto(jsonText: String): PairsInfoDto =
        jsonAdapter.fromJson(jsonText) ?: getEmptyInfo()

    private fun getEmptyInfo(): PairsInfoDto =
        PairsInfoDto(
            timezone = "",
            serverTime = 0L,
            rateLimits = listOf(RateLimit(RateLimitType.ORDERS, RateLimitInterval.DAY, 0L)),
            exchangeFilters = emptyList(),
            symbols = emptyList()
        )
}
