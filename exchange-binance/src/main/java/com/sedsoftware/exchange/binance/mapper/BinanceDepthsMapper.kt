package com.sedsoftware.exchange.binance.mapper

import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.core.domain.entity.ExchangeType
import com.sedsoftware.exchange.binance.common.params.OrderSide
import com.sedsoftware.exchange.binance.database.model.BinanceDepthDbModel
import com.sedsoftware.exchange.binance.entity.BinanceCurrency
import com.sedsoftware.exchange.binance.entity.BinanceCurrencyPair
import com.sedsoftware.exchange.binance.entity.BinanceCurrencyPairDepth
import com.sedsoftware.exchange.binance.network.model.dto.PairDepthDto
import javax.inject.Inject

class BinanceDepthsMapper @Inject constructor() {

    fun mapFromCloudToDb(pair: CurrencyPair, depthsInfo: PairDepthDto): List<BinanceDepthDbModel> {

        val result = mutableListOf<BinanceDepthDbModel>()

        depthsInfo.bids.forEach { bid ->
            val entity = BinanceDepthDbModel(
                symbol = pair.baseCurrency.name + pair.marketCurrency.name,
                baseCurrencyName = pair.baseCurrency.name,
                baseCurrencyLabel = pair.baseCurrency.label,
                marketCurrencyName = pair.marketCurrency.name,
                marketCurrencyLabel = pair.marketCurrency.label,
                amount = bid[1].toString().toFloat(),
                price = bid[0].toString().toFloat(),
                total = bid[0].toString().toFloat() * bid[1].toString().toFloat(),
                orderSide = OrderSide.SELL
            )
            result.add(entity)
        }

        depthsInfo.asks.forEach { ask ->
            val entity = BinanceDepthDbModel(
                symbol = pair.baseCurrency.name + pair.marketCurrency.name,
                baseCurrencyName = pair.baseCurrency.name,
                baseCurrencyLabel = pair.baseCurrency.label,
                marketCurrencyName = pair.marketCurrency.name,
                marketCurrencyLabel = pair.marketCurrency.label,
                amount = ask[1].toString().toFloat(),
                price = ask[0].toString().toFloat(),
                total = ask[0].toString().toFloat() * ask[1].toString().toFloat(),
                orderSide = OrderSide.BUY
            )
            result.add(entity)
        }

        return result
    }

    fun mapFromDbToEntity(from: BinanceDepthDbModel): BinanceCurrencyPairDepth {

        val currencyPair = BinanceCurrencyPair(
            exchange = ExchangeType.BINANCE,
            baseCurrency = enumValueOf<BinanceCurrency>(from.baseCurrencyName),
            marketCurrency = enumValueOf<BinanceCurrency>(from.marketCurrencyName),
            symbol = from.symbol
        )

        return BinanceCurrencyPairDepth(
            pair = currencyPair,
            amount = from.amount,
            price = from.price,
            total = from.total,
            isBid = from.orderSide == OrderSide.SELL
        )
    }
}
