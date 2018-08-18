package com.sedsoftware.binance.mapper

import com.sedsoftware.binance.database.model.BinanceTradeDbModel
import com.sedsoftware.binance.entity.BinanceCurrencyPairTrade
import com.sedsoftware.binance.network.model.SymbolTradeModel
import javax.inject.Inject

class BinanceTradesMapper @Inject constructor() {

    fun mapFromDbToEntity(from: BinanceTradeDbModel): BinanceCurrencyPairTrade =
        BinanceCurrencyPairTrade(
            id = from.id,
            timestamp = from.time,
            quantity = from.qty,
            price = from.price,
            total = from.total,
            isBuy = from.isBuyerMaker
        )

    fun mapFromCloudToDb(from: List<SymbolTradeModel>): List<BinanceTradeDbModel> =
        from.map { mapTradeToDb(it) }

    private fun mapTradeToDb(from: SymbolTradeModel): BinanceTradeDbModel =
        BinanceTradeDbModel(
            id = from.id,
            time = from.time,
            qty = from.qty,
            price = from.price,
            total = from.qty * from.price,
            isBuyerMaker = from.isBuyerMaker,
            isBestMatch = from.isBestMatch
        )
}
