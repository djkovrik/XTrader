package com.sedsoftware.exchange.binance.mapper

import com.sedsoftware.core.domain.ExchangeType
import com.sedsoftware.core.domain.interactor.CurrencyManager
import com.sedsoftware.exchange.binance.database.model.BinanceSymbolDbModel
import com.sedsoftware.exchange.binance.database.model.BinanceSyncInfoDbModel
import com.sedsoftware.exchange.binance.network.model.PairsInfo
import com.sedsoftware.exchange.binance.network.model.SymbolInfoModel
import javax.inject.Inject

class BinanceSymbolsMapper @Inject constructor(
    private val currencyManager: CurrencyManager
) {

    suspend fun mapSymbolsToDb(from: PairsInfo): List<BinanceSymbolDbModel> =
        from.symbols.map { mapSymbolToDb(it) }

    fun mapSyncInfoToDb(from: PairsInfo): BinanceSyncInfoDbModel =
        BinanceSyncInfoDbModel(
            name = ExchangeType.BINANCE.name,
            lastSyncDate = from.serverTime
        )

    private suspend fun mapSymbolToDb(from: SymbolInfoModel): BinanceSymbolDbModel {

        val baseSymbolName = currencyManager.getCurrency(from.baseAsset).label
        val quoteSymbolName = currencyManager.getCurrency(from.quoteAsset).label

        return BinanceSymbolDbModel(
            symbol = from.symbol,
            status = from.status,
            baseAsset = from.baseAsset,
            baseAssetName = baseSymbolName,
            baseAssetPrecision = from.baseAssetPrecision,
            quoteAsset = from.quoteAsset,
            quoteAssetName = quoteSymbolName,
            quotePrecision = from.quotePrecision,
            orderTypes = from.orderTypes,
            icebergAllowed = from.icebergAllowed,
            filters = from.filters
        )
    }
}
