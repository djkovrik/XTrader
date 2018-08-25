package com.sedsoftware.binance.fakedata

import com.sedsoftware.binance.common.params.FilterType
import com.sedsoftware.binance.common.params.OrderType
import com.sedsoftware.binance.common.params.SymbolStatus
import com.sedsoftware.binance.fakedata.json.SymbolsInfo
import com.sedsoftware.binance.network.model.SymbolInfoModel
import com.sedsoftware.binance.network.model.common.Filter
import com.sedsoftware.binance.network.model.common.RateLimit
import com.sedsoftware.binance.network.model.dto.PairsInfoDto
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.Date

class FakeInfoData {

    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .build()
    }

    private val jsonAdapter: JsonAdapter<PairsInfoDto> by lazy {
        return@lazy moshi.adapter(PairsInfoDto::class.java)
    }

    fun getRawParsedData(): PairsInfoDto =
        jsonAdapter.fromJson(SymbolsInfo.JSON) ?: getEmptyInfo()


    fun getPredefinedParsedDto(): PairsInfoDto =
        PairsInfoDto(
            timezone = "UTC",
            serverTime = 1535202553717L,
            rateLimits = listOf(
                RateLimit(
                    rateLimitType = "REQUEST_WEIGHT",
                    interval = "MINUTE",
                    limit = 1200L
                ),
                RateLimit(
                    rateLimitType = "ORDERS",
                    interval = "SECOND",
                    limit = 10L
                ),
                RateLimit(
                    rateLimitType = "ORDERS",
                    interval = "DAY",
                    limit = 100000L
                )
            ),
            exchangeFilters = emptyList(),
            symbols = listOf(
                SymbolInfoModel(
                    symbol = "ETHBTC",
                    status = SymbolStatus.TRADING,
                    baseAsset = "ETH",
                    baseAssetPrecision = 8,
                    quoteAsset = "BTC",
                    quotePrecision = 8,
                    orderTypes = listOf(
                        OrderType.LIMIT,
                        OrderType.LIMIT_MAKER,
                        OrderType.MARKET,
                        OrderType.STOP_LOSS_LIMIT,
                        OrderType.TAKE_PROFIT_LIMIT
                    ),
                    icebergAllowed = true,
                    filters = listOf(
                        Filter(
                            filterType = FilterType.PRICE_FILTER,
                            minPrice = "0.00416600",
                            maxPrice = "0.41657500",
                            tickSize = "0.00000100"
                        ),
                        Filter(
                            filterType = FilterType.LOT_SIZE,
                            minQty = "0.00100000",
                            maxQty = "100000.00000000",
                            stepSize = "0.00100000"
                        ),
                        Filter(
                            filterType = FilterType.MIN_NOTIONAL,
                            minNotional = "0.00100000"
                        ),
                        Filter(
                            filterType = FilterType.ICEBERG_PARTS,
                            limit = 10
                        ),
                        Filter(
                            filterType = FilterType.MAX_NUM_ALGO_ORDERS,
                            maxNumAlgoOrders = 5
                        )
                    )
                ),
                SymbolInfoModel(
                    symbol = "HCETH",
                    status = SymbolStatus.TRADING,
                    baseAsset = "HC",
                    baseAssetPrecision = 8,
                    quoteAsset = "ETH",
                    quotePrecision = 8,
                    orderTypes = listOf(
                        OrderType.LIMIT,
                        OrderType.LIMIT_MAKER,
                        OrderType.MARKET,
                        OrderType.STOP_LOSS_LIMIT,
                        OrderType.TAKE_PROFIT_LIMIT
                    ),
                    icebergAllowed = true,
                    filters = listOf(
                        Filter(
                            filterType = FilterType.PRICE_FILTER,
                            minPrice = "0.00099400",
                            maxPrice = "0.09934000",
                            tickSize = "0.00000100"
                        ),
                        Filter(
                            filterType = FilterType.LOT_SIZE,
                            minQty = "0.01000000",
                            maxQty = "90000000.00000000",
                            stepSize = "0.01000000"
                        ),
                        Filter(
                            filterType = FilterType.MIN_NOTIONAL,
                            minNotional = "0.01000000"
                        ),
                        Filter(
                            filterType = FilterType.ICEBERG_PARTS,
                            limit = 10
                        ),
                        Filter(
                            filterType = FilterType.MAX_NUM_ALGO_ORDERS,
                            maxNumAlgoOrders = 5
                        )
                    )
                )
            )
        )

    private fun getEmptyInfo(): PairsInfoDto =
        PairsInfoDto(
            timezone = "",
            serverTime = 0L,
            rateLimits = listOf(RateLimit("ORDERS", "DAY", 0L)),
            exchangeFilters = emptyList(),
            symbols = emptyList()
        )
}
