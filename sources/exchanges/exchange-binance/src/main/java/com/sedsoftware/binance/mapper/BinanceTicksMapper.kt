package com.sedsoftware.binance.mapper

import com.sedsoftware.binance.database.model.BinanceTickDbModel
import com.sedsoftware.binance.entity.BinanceCurrencyPairTick
import com.sedsoftware.binance.network.model.SymbolTickModel
import com.sedsoftware.coreentity.ExchangeType
import javax.inject.Inject

class BinanceTicksMapper @Inject constructor() {

    fun mapFromCloudToDb(from: SymbolTickModel): BinanceTickDbModel =
        BinanceTickDbModel(
            symbol = from.symbol,
            bidPrice = from.bidPrice.toFloat(),
            bidQty = from.bidQty.toFloat(),
            askPrice = from.askPrice.toFloat(),
            askQty = from.askQty.toFloat()
        )

    fun mapFromDbToEntity(from: BinanceTickDbModel): BinanceCurrencyPairTick =
        BinanceCurrencyPairTick(
            exchange = ExchangeType.BINANCE,
            symbol = from.symbol,
            bidPrice = from.bidPrice,
            bidQuantity = from.bidQty,
            askPrice = from.askPrice,
            askQuantity = from.askQty
        )
}
