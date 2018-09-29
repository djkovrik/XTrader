package com.sedsoftware.exchange.binance.mapper

import com.sedsoftware.exchange.binance.database.model.BinanceTradeDbModel
import com.sedsoftware.exchange.binance.entity.BinanceCurrencyPair
import com.sedsoftware.exchange.binance.entity.BinanceCurrencyPairTrade
import com.sedsoftware.exchange.binance.network.model.SymbolTradeModel
import javax.inject.Inject

class BinanceTradesMapper @Inject constructor() {

    fun mapFromCloudToDb(pair: BinanceCurrencyPair, from: SymbolTradeModel): BinanceTradeDbModel =
        BinanceTradeDbModel(
            id = from.id,
            time = from.time,
            symbol = pair.symbol,
            qty = from.qty.toFloat(),
            price = from.price.toFloat(),
            total = from.qty.toFloat() * from.price.toFloat(),
            isBuyerMaker = from.isBuyerMaker,
            isBestMatch = from.isBestMatch
        )

    fun mapFromDbToEntity(pair: BinanceCurrencyPair, from: BinanceTradeDbModel): BinanceCurrencyPairTrade =
        BinanceCurrencyPairTrade(
            pair = pair,
            id = from.id,
            timestamp = from.time,
            quantity = from.qty,
            price = from.price,
            total = from.total,
            isBuy = from.isBuyerMaker
        )
}
