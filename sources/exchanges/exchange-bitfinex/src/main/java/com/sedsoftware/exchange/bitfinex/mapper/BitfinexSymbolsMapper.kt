package com.sedsoftware.exchange.bitfinex.mapper

import com.sedsoftware.core.domain.ExchangeType
import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.core.domain.entity.CurrencyPairTick
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.CurrencyManager
import com.sedsoftware.exchange.bitfinex.database.model.BitfinexPairTickDbModel
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

    fun mapDbTickToEntity(from: BitfinexPairTickDbModel): CurrencyPairTick =
        object : CurrencyPairTick {
            override val pair: CurrencyPair =
                object : CurrencyPair {
                    override val exchange: Exchange = ExchangeType.BINANCE
                    override val baseCurrency: Currency =
                        object : Currency {
                            override val name: String = from.baseCurrencyName
                            override val label: String = from.baseCurrencyLabel
                        }
                    override val marketCurrency: Currency =
                        object : Currency {
                            override val name: String = from.quoteCurrencyName
                            override val label: String = from.quoteCurrencyLabel
                        }
                    override val symbol: String = from.symbol
                }
            override val price: Float = from.currentPrice
            override val percentChange: Float = from.percentChange
            override val valueChange: Float = from.valueChange
            override val added: Long = from.insertionDate.toEpochSecond()
            override val refreshed: Long = from.refreshDate.toEpochSecond()
        }

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

    private fun splitSymbol(symbol: String): Pair<String, String> =
        if (symbol.contains(":")) {
            symbol.substring(0, symbol.indexOf(":")) to symbol.substring(symbol.indexOf(":") + 1)
        } else {
            symbol.substring(0, 3) to symbol.substring(3)
        }
}
