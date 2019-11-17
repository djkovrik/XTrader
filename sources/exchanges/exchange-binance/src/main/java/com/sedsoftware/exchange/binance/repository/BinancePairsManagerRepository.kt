package com.sedsoftware.exchange.binance.repository

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.exchange.binance.common.params.SymbolStatus
import com.sedsoftware.exchange.binance.database.BinanceDatabase
import com.sedsoftware.exchange.binance.database.dao.BinanceSymbolsDao
import javax.inject.Inject

class BinancePairsManagerRepository @Inject constructor(
    private val db: BinanceDatabase
) {

    private val symbolsDao: BinanceSymbolsDao by lazy {
        db.getBinanceSymbolsDao()
    }

    suspend fun getBaseCurrencies(): List<Currency> =
        symbolsDao
            .getBaseCurrencies()
            .filter { it.status == SymbolStatus.TRADING }
            .map {
                object : Currency {
                    override val name: String = it.baseAsset
                    override val label: String = it.baseAssetName
                }
            }

    suspend fun getMarketCurrencies(base: Currency): List<Currency> =
        symbolsDao
            .getCurrenciesForBase(base.name)
            .filter { it.status == SymbolStatus.TRADING }
            .map {
                object : Currency {
                    override val name: String = it.quoteAsset
                    override val label: String = it.quoteAssetName
                }
            }
}
