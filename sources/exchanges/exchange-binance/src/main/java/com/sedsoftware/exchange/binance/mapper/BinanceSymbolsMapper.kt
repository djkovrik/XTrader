package com.sedsoftware.exchange.binance.mapper

import com.sedsoftware.core.domain.ExchangeType
import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.exchange.binance.database.model.BinanceSymbolDbModel
import com.sedsoftware.exchange.binance.entity.BinanceCurrency
import com.sedsoftware.exchange.binance.entity.BinanceCurrencyPair
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

    fun mapBaseSymbolsFromDb(from: List<BinanceSymbolDbModel>): List<Currency> =
        from.map { enumValueOf<BinanceCurrency>(it.baseAsset) }

    fun mapMarketPairsFromDb(from: List<BinanceSymbolDbModel>): List<CurrencyPair> =
        from.map {
            BinanceCurrencyPair(
                exchange = ExchangeType.BINANCE,
                baseCurrency = enumValueOf<BinanceCurrency>(it.baseAsset),
                marketCurrency = enumValueOf<BinanceCurrency>(it.quoteAsset),
                symbol = it.symbol
            )
        }
}
