package com.sedsoftware.exchange.binance.repository

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.exchange.binance.database.BinanceDatabase
import com.sedsoftware.exchange.binance.database.dao.BinanceSymbolsDao
import com.sedsoftware.exchange.binance.mapper.BinanceSymbolsMapper
import javax.inject.Inject

class PairsManagerRepository @Inject constructor(
    private val db: BinanceDatabase,
    private val mapper: BinanceSymbolsMapper
) {

    private val symbolsDao: BinanceSymbolsDao by lazy {
        db.getBinanceSymbolsDao()
    }

    suspend fun getBaseCurrencies(): List<Currency> =
        symbolsDao
            .getAllCurrencies()
            .map(mapper::mapDbToEntity)

    suspend fun getMarketCurrencies(base: Currency): List<Currency> =
        symbolsDao
            .getCurrenciesForBase(base.name)
            .map(mapper::mapDbToEntity)
}
