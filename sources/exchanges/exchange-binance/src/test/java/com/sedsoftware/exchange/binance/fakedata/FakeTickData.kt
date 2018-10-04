package com.sedsoftware.exchange.binance.fakedata

import com.sedsoftware.core.domain.entity.ExchangeType
import com.sedsoftware.exchange.binance.database.model.BinanceTickDbModel
import com.sedsoftware.exchange.binance.entity.BinanceCurrencyPairTick
import com.sedsoftware.exchange.binance.network.model.SymbolTickModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.Date

class FakeTickData {

    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .build()
    }

    private val jsonAdapter: JsonAdapter<SymbolTickModel> by lazy {
        return@lazy moshi.adapter(SymbolTickModel::class.java)
    }

    fun getRawParsedEntity(jsonText: String): SymbolTickModel =
        jsonAdapter.fromJson(jsonText) ?: SymbolTickModel("", "", "", "", "")


    fun getPredefinedParsedEntity(): SymbolTickModel =
        SymbolTickModel(
            symbol = "LTCBTC",
            bidPrice = "0.00854200",
            bidQty = "0.78000000",
            askPrice = "0.00855800",
            askQty = "13.39000000"
        )

    fun getPredefinedDatabaseEntity(): BinanceTickDbModel =
        BinanceTickDbModel(
            symbol = "LTCBTC",
            bidPrice = 0.008542f,
            bidQty = 0.78f,
            askPrice = 0.008558f,
            askQty = 13.39f
        )

    fun getPredefinedDomainEntity(): BinanceCurrencyPairTick =
        BinanceCurrencyPairTick(
            exchange = ExchangeType.BINANCE,
            symbol = "LTCBTC",
            bidPrice = 0.008542f,
            bidQuantity = 0.78f,
            askPrice = 0.008558f,
            askQuantity = 13.39f
        )
}
