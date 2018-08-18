package com.sedsoftware.binance.mapper

import com.sedsoftware.binance.entity.BinanceCurrency
import com.sedsoftware.binance.entity.BinanceCurrencyPair
import com.sedsoftware.binance.entity.BinanceCurrencyPairDepth
import com.sedsoftware.binance.entity.BinanceExchange
import com.sedsoftware.binance.common.params.OrderSide
import com.sedsoftware.binance.database.model.BinanceDepthDbModel
import com.sedsoftware.core.entity.CurrencyPair
import com.sedsoftware.core.entity.info.CurrencyPairDepth
import javax.inject.Inject

class BinanceDepthsMapper @Inject constructor() {

    fun mapFromDbToEntity(from: BinanceDepthDbModel): CurrencyPairDepth {

        val currencyPair = BinanceCurrencyPair(
            exchange = BinanceExchange.BINANCE,
            baseCurrency = enumValueOf<BinanceCurrency>(from.baseCurrencyName),
            marketCurrency = enumValueOf<BinanceCurrency>(from.marketCurrencyName),
            symbol = from.baseCurrencyName + from.marketCurrencyName
        )

        return BinanceCurrencyPairDepth(
            pair = currencyPair,
            amount = from.amount,
            price = from.price,
            total = from.total,
            isBid = from.orderSide == OrderSide.BUY
        )
    }

    fun mapFromCloudToDb(pair: CurrencyPair, list: List<Pair<String, String>>, isBid: Boolean): List<BinanceDepthDbModel> =
        list.map { mapPairToDb(pair, it, isBid) }

    private fun mapPairToDb(pair: CurrencyPair, element: Pair<String, String>, isBid: Boolean): BinanceDepthDbModel =
        BinanceDepthDbModel(
            baseCurrencyName = pair.baseCurrency.name,
            baseCurrencyLabel = pair.baseCurrency.label,
            marketCurrencyName = pair.marketCurrency.name,
            marketCurrencyLabel = pair.marketCurrency.label,
            amount = element.first.toFloat(),
            price = element.second.toFloat(),
            total = element.first.toFloat() * element.second.toFloat(),
            orderSide = if (isBid) OrderSide.SELL else OrderSide.BUY
        )
}
