package com.sedsoftware.exchange.binance.mapper

import com.sedsoftware.exchange.binance.database.model.BinanceSymbolDbModel
import com.sedsoftware.exchange.binance.network.model.SymbolInfoModel
import javax.inject.Inject

class BinanceSymbolsMapper @Inject constructor() {

    fun mapFromCloudToDb(from: SymbolInfoModel, timestamp: Long): BinanceSymbolDbModel =
        BinanceSymbolDbModel(
            symbol = from.symbol,
            syncDate = timestamp,
            status = from.status,
            baseAsset = from.baseAsset,
            baseAssetPrecision = from.baseAssetPrecision,
            quoteAsset = from.quoteAsset,
            quotePrecision = from.quotePrecision,
            orderTypes = from.orderTypes,
            icebergAllowed = from.icebergAllowed,
            filters = from.filters
        )
}
