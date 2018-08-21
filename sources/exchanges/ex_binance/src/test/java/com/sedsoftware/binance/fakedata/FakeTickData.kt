package com.sedsoftware.binance.fakedata

import com.sedsoftware.binance.database.model.BinanceTickDbModel
import com.sedsoftware.binance.entity.BinanceCurrencyPairTick
import com.sedsoftware.binance.entity.BinanceExchange
import com.sedsoftware.binance.fakedata.json.SymbolTick
import com.sedsoftware.binance.network.model.SymbolTickModel
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

    fun getRawParsedData(): SymbolTickModel =
        jsonAdapter.fromJson(SymbolTick.JSON) ?: SymbolTickModel("", "", "", "", "")


    fun getPredefinedParsedEntity(): SymbolTickModel =
        SymbolTickModel(
            symbol = "LTCBTC",
            bidPrice = "0.00854200",
            bidQty = "0.78000000",
            askPrice = "0.00855800",
            askQty = "13.39000000"
        )

    fun getDatabasEntity(): BinanceTickDbModel =
        BinanceTickDbModel(
            symbol = "LTCBTC",
            bidPrice = 0.008542f,
            bidQty = 0.78f,
            askPrice = 0.008558f,
            askQty = 13.39f
        )

    fun getDomainEntity(): BinanceCurrencyPairTick =
        BinanceCurrencyPairTick(
            exchange = BinanceExchange.BINANCE,
            symbol = "LTCBTC",
            bidPrice = 0.008542f,
            bidQuantity = 0.78f,
            askPrice = 0.008558f,
            askQuantity = 13.39f
        )
}
