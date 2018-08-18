package com.sedsoftware.binance.mapper

import com.sedsoftware.binance.common.BinanceCurrency
import com.sedsoftware.binance.database.model.BinanceSymbol
import com.sedsoftware.binance.network.model.SymbolInfo
import com.sedsoftware.core.entity.Currency
import java.util.Date
import javax.inject.Inject

class BinanceSymbolsMapper @Inject constructor() {

    fun mapBaseSymbolsFromDb(from: List<BinanceSymbol>): List<Currency> =
        from.map { enumValueOf<BinanceCurrency>(it.baseAsset) }

    fun mapMarketSymbolsFromDb(from: List<BinanceSymbol>): List<Currency> =
        from.map { enumValueOf<BinanceCurrency>(it.quoteAsset) }

    fun mapFromCloudToDb(from: List<SymbolInfo>, currentDate: Date): List<BinanceSymbol> =
        from.map { mapSymbolToDb(it, currentDate) }

    private fun mapSymbolToDb(from: SymbolInfo, date: Date): BinanceSymbol =
        BinanceSymbol(
            symbol = from.symbol,
            syncDate = date,
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
