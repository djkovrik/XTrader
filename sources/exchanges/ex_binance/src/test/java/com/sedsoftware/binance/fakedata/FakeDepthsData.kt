package com.sedsoftware.binance.fakedata

import com.sedsoftware.binance.common.params.OrderSide
import com.sedsoftware.binance.database.model.BinanceDepthDbModel
import com.sedsoftware.binance.entity.BinanceCurrencyPair
import com.sedsoftware.binance.entity.BinanceCurrencyPairDepth
import com.sedsoftware.binance.network.model.dto.PairDepthDto
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.Date

class FakeDepthsData {

    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .build()
    }

    private val jsonAdapter: JsonAdapter<PairDepthDto> by lazy {
        val filtersList = Types.newParameterizedType(PairDepthDto::class.java, List::class.java, Any::class.java)
        return@lazy moshi.adapter<PairDepthDto>(filtersList)
    }

    fun getRawParsedDto(jsonText: String): PairDepthDto =
        jsonAdapter.fromJson(jsonText) ?: PairDepthDto(0L, emptyList(), emptyList())

    fun getPredefinedParsedDto(): PairDepthDto =
        PairDepthDto(
            lastUpdateId = 104388219L,
            bids = listOf(
                listOf(
                    "0.00860800",
                    "192.96000000",
                    emptyList<Any>()
                ),
                listOf(
                    "0.00860100",
                    "14.57000000",
                    emptyList<Any>()
                ),
                listOf(
                    "0.00848300",
                    "0.12000000",
                    emptyList<Any>()
                )
            ),
            asks = listOf(
                listOf(
                    "0.00860900",
                    "3.43000000",
                    emptyList<Any>()
                ),
                listOf(
                    "0.00861000",
                    "897.42000000",
                    emptyList<Any>()
                ),
                listOf(
                    "0.00875900",
                    "1.60000000",
                    emptyList<Any>()
                )
            )
        )

    fun getPredefinedDatabaseEntities(pair: BinanceCurrencyPair): List<BinanceDepthDbModel> =
        listOf(
            BinanceDepthDbModel(
                symbol = pair.baseCurrency.name + pair.marketCurrency.name,
                baseCurrencyName = pair.baseCurrency.name,
                baseCurrencyLabel = pair.baseCurrency.label,
                marketCurrencyName = pair.marketCurrency.name,
                marketCurrencyLabel = pair.marketCurrency.label,
                amount = 192.96f,
                price = 0.008608f,
                total = 192.96f * 0.008608f,
                orderSide = OrderSide.SELL
            ),
            BinanceDepthDbModel(
                symbol = pair.baseCurrency.name + pair.marketCurrency.name,
                baseCurrencyName = pair.baseCurrency.name,
                baseCurrencyLabel = pair.baseCurrency.label,
                marketCurrencyName = pair.marketCurrency.name,
                marketCurrencyLabel = pair.marketCurrency.label,
                amount = 14.57f,
                price = 0.008601f,
                total = 14.57f * 0.008601f,
                orderSide = OrderSide.SELL
            ),
            BinanceDepthDbModel(
                symbol = pair.baseCurrency.name + pair.marketCurrency.name,
                baseCurrencyName = pair.baseCurrency.name,
                baseCurrencyLabel = pair.baseCurrency.label,
                marketCurrencyName = pair.marketCurrency.name,
                marketCurrencyLabel = pair.marketCurrency.label,
                amount = 0.12f,
                price = 0.008483f,
                total = 0.12f * 0.008483f,
                orderSide = OrderSide.SELL
            ),
            BinanceDepthDbModel(
                symbol = pair.baseCurrency.name + pair.marketCurrency.name,
                baseCurrencyName = pair.baseCurrency.name,
                baseCurrencyLabel = pair.baseCurrency.label,
                marketCurrencyName = pair.marketCurrency.name,
                marketCurrencyLabel = pair.marketCurrency.label,
                amount = 3.43f,
                price = 0.008609f,
                total = 3.43f * 0.008609f,
                orderSide = OrderSide.BUY
            ),
            BinanceDepthDbModel(
                symbol = pair.baseCurrency.name + pair.marketCurrency.name,
                baseCurrencyName = pair.baseCurrency.name,
                baseCurrencyLabel = pair.baseCurrency.label,
                marketCurrencyName = pair.marketCurrency.name,
                marketCurrencyLabel = pair.marketCurrency.label,
                amount = 897.42f,
                price = 0.00861f,
                total = 897.42f * 0.00861f,
                orderSide = OrderSide.BUY
            ),
            BinanceDepthDbModel(
                symbol = pair.baseCurrency.name + pair.marketCurrency.name,
                baseCurrencyName = pair.baseCurrency.name,
                baseCurrencyLabel = pair.baseCurrency.label,
                marketCurrencyName = pair.marketCurrency.name,
                marketCurrencyLabel = pair.marketCurrency.label,
                amount = 1.6f,
                price = 0.008759f,
                total = 1.6f * 0.008759f,
                orderSide = OrderSide.BUY
            )
        )

    fun getPredefinedDomainEntities(pair: BinanceCurrencyPair): List<BinanceCurrencyPairDepth> =
        listOf(
            BinanceCurrencyPairDepth(
                pair = pair,
                amount = 192.96f,
                price = 0.008608f,
                total = 192.96f * 0.008608f,
                isBid = true
            ),
            BinanceCurrencyPairDepth(
                pair = pair,
                amount = 14.57f,
                price = 0.008601f,
                total = 14.57f * 0.008601f,
                isBid = true
            ),
            BinanceCurrencyPairDepth(
                pair = pair,
                amount = 0.12f,
                price = 0.008483f,
                total = 0.12f * 0.008483f,
                isBid = true
            ),
            BinanceCurrencyPairDepth(
                pair = pair,
                amount = 3.43f,
                price = 0.008609f,
                total = 3.43f * 0.008609f,
                isBid = false
            ),
            BinanceCurrencyPairDepth(
                pair = pair,
                amount = 897.42f,
                price = 0.00861f,
                total = 897.42f * 0.00861f,
                isBid = false
            ),
            BinanceCurrencyPairDepth(
                pair = pair,
                amount = 1.6f,
                price = 0.008759f,
                total = 1.6f * 0.008759f,
                isBid = false
            )
        )
}
