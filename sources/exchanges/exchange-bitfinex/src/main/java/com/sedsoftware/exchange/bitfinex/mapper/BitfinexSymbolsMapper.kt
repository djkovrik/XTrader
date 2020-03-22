package com.sedsoftware.exchange.bitfinex.mapper

import com.sedsoftware.core.domain.ExchangeType
import com.sedsoftware.core.domain.interactor.CurrencyManager
import com.sedsoftware.exchange.bitfinex.database.model.BitfinexSymbolDbModel
import com.sedsoftware.exchange.bitfinex.database.model.BitfinexSyncInfoDbModel
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

class BitfinexSymbolsMapper @Inject constructor(
    private val currencyManager: CurrencyManager
) {

    suspend fun mapSymbolsToDb(from: List<String>): List<BitfinexSymbolDbModel> =
        from.map { mapSymbolToDb(it) }

    fun makeSyncInfoDbModel(): BitfinexSyncInfoDbModel =
        BitfinexSyncInfoDbModel(
            name = ExchangeType.BITFINEX.name,
            lastSyncDate = OffsetDateTime.now()
        )

    private suspend fun mapSymbolToDb(symbol: String): BitfinexSymbolDbModel {

        val splitted = splitSymbol(symbol)
        val baseSymbol = splitted.first.toUpperCase()
        val quoteSymbol = splitted.second.toUpperCase()
        val baseSymbolName = currencyManager.getCurrency(baseSymbol).label
        val quoteSymbolName = currencyManager.getCurrency(quoteSymbol).label

        return BitfinexSymbolDbModel(
            symbol = symbol.toUpperCase(),
            baseAsset = baseSymbol,
            baseAssetName = baseSymbolName,
            quoteAsset = quoteSymbol,
            quoteAssetName = quoteSymbolName
        )
    }

    internal fun splitSymbol(symbol: String): Pair<String, String> =
        if (symbol.contains(":")) {
            symbol.substring(0, symbol.indexOf(":")) to symbol.substring(symbol.indexOf(":") + 1)
        } else {
            symbol.substring(0, 3) to symbol.substring(3)
        }
}
