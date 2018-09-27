package com.sedsoftware.binance.fakedata

import com.sedsoftware.binance.database.model.BinanceTradeDbModel
import com.sedsoftware.binance.entity.BinanceCurrency
import com.sedsoftware.binance.entity.BinanceCurrencyPair
import com.sedsoftware.binance.entity.BinanceCurrencyPairTrade
import com.sedsoftware.binance.network.model.SymbolTradeModel
import com.sedsoftware.coreentity.ExchangeType
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.Date

class FakeTradesData {

    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .build()
    }

    private val jsonAdapter: JsonAdapter<List<SymbolTradeModel>> by lazy {
        val filtersList = Types.newParameterizedType(List::class.java, SymbolTradeModel::class.java)
        return@lazy moshi.adapter<List<SymbolTradeModel>>(filtersList)
    }

    fun getRawParsedEntities(jsonText: String): List<SymbolTradeModel> =
        jsonAdapter.fromJson(jsonText) ?: emptyList()


    fun getPredefinedParsedEntities(): List<SymbolTradeModel> =
        listOf(
            SymbolTradeModel(
                id = 15340801,
                price = "0.00858900",
                qty = "12.25000000",
                time = 1534957951247,
                isBuyerMaker = true,
                isBestMatch = true
            ),
            SymbolTradeModel(
                id = 15340802,
                price = "0.00858900",
                qty = "19.12000000",
                time = 1534957951264,
                isBuyerMaker = true,
                isBestMatch = true
            ),
            SymbolTradeModel(
                id = 15341300,
                price = "0.00855300",
                qty = "0.59000000",
                time = 1534960156201,
                isBuyerMaker = false,
                isBestMatch = true
            )
        )

    fun getPredefinedDatabaseEntities(): List<BinanceTradeDbModel> =
        listOf(
            BinanceTradeDbModel(
                id = 15340801,
                time = 1534957951247,
                symbol = "LTCBTC",
                qty = 12.25f,
                price = 0.008589f,
                total = 12.25f * 0.008589f,
                isBuyerMaker = true,
                isBestMatch = true
            ),
            BinanceTradeDbModel(
                id = 15340802,
                time = 1534957951264,
                symbol = "LTCBTC",
                qty = 19.12f,
                price = 0.008589f,
                total = 19.12f * 0.008589f,
                isBuyerMaker = true,
                isBestMatch = true
            ),
            BinanceTradeDbModel(
                id = 15341300,
                time = 1534960156201,
                symbol = "LTCBTC",
                qty = 0.59f,
                price = 0.008553f,
                total = 0.59f * 0.008553f,
                isBuyerMaker = false,
                isBestMatch = true
            )
        )

    fun getPredefinedDomainEntities(): List<BinanceCurrencyPairTrade> =
        listOf(
            BinanceCurrencyPairTrade(
                id = 15340801,
                pair = BinanceCurrencyPair(
                    exchange = ExchangeType.BINANCE,
                    baseCurrency = BinanceCurrency.LTC,
                    marketCurrency = BinanceCurrency.BTC,
                    symbol = "LTCBTC"
                ),
                timestamp = 1534957951247,
                quantity = 12.25f,
                price = 0.008589f,
                total = 12.25f * 0.008589f,
                isBuy = true
            ),
            BinanceCurrencyPairTrade(
                id = 15340802,
                pair = BinanceCurrencyPair(
                    exchange = ExchangeType.BINANCE,
                    baseCurrency = BinanceCurrency.LTC,
                    marketCurrency = BinanceCurrency.BTC,
                    symbol = "LTCBTC"
                ),
                timestamp = 1534957951264,
                quantity = 19.12f,
                price = 0.008589f,
                total = 19.12f * 0.008589f,
                isBuy = true
            ),
            BinanceCurrencyPairTrade(
                id = 15341300,
                pair = BinanceCurrencyPair(
                    exchange = ExchangeType.BINANCE,
                    baseCurrency = BinanceCurrency.LTC,
                    marketCurrency = BinanceCurrency.BTC,
                    symbol = "LTCBTC"
                ),
                timestamp = 1534960156201,
                quantity = 0.59f,
                price = 0.008553f,
                total = 0.59f * 0.008553f,
                isBuy = false
            )
        )
}
