package com.sedsoftware.exchange.binance.mapper

import com.sedsoftware.core.domain.ExchangeType
import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.exchange.binance.database.model.BinanceSymbolDbModel
import com.sedsoftware.exchange.binance.database.model.BinanceSyncInfoDbModel
import com.sedsoftware.exchange.binance.entity.BinanceCurrency
import com.sedsoftware.exchange.binance.network.model.PairsInfo
import com.sedsoftware.exchange.binance.network.model.SymbolInfoModel
import javax.inject.Inject

class BinanceSymbolsMapper @Inject constructor() {

    fun mapSymbolsToDb(from: PairsInfo): List<BinanceSymbolDbModel> =
        from.symbols.map { mapSymbolToDb(it) }

    fun mapSyncInfoToDb(from: PairsInfo): BinanceSyncInfoDbModel =
        BinanceSyncInfoDbModel(
            name = ExchangeType.BINANCE.name,
            lastSyncDate = from.serverTime
        )

    fun mapDbToBaseCurrency(from: BinanceSymbolDbModel): Currency =
        BinanceCurrency.valueOf(from.baseAsset)

    fun mapDbToMarketCurrency(from: BinanceSymbolDbModel): Currency =
        BinanceCurrency.valueOf(from.quoteAsset)

    private fun mapSymbolToDb(from: SymbolInfoModel): BinanceSymbolDbModel =
        BinanceSymbolDbModel(
            symbol = from.symbol,
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
