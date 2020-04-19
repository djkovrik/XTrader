package com.sedsoftware.exchange.binance.repository

import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.repository.PairsManagerRepository
import com.sedsoftware.exchange.binance.common.params.SymbolStatus
import com.sedsoftware.exchange.binance.database.BinanceDatabase
import com.sedsoftware.exchange.binance.database.dao.BinanceSymbolsDao
import com.sedsoftware.exchange.binance.database.dao.BinanceSyncInfoDao
import javax.inject.Inject

class BinancePairsManagerRepository @Inject constructor(
    private val db: BinanceDatabase
) : PairsManagerRepository {

    private val symbolsDao: BinanceSymbolsDao by lazy {
        db.getBinanceSymbolsDao()
    }

    private val syncInfoDao: BinanceSyncInfoDao by lazy {
        db.getBinanceSyncInfoDao()
    }

    override suspend fun isSynchronized(): Boolean =
        syncInfoDao.getLastSyncDate() != null

    override suspend fun getBaseCurrencies(): List<Currency> =
        symbolsDao
            .getBaseCurrencies()
            .filter { it.status == SymbolStatus.TRADING }
            .map {
                object : Currency {
                    override val name: String = it.baseAsset
                    override val label: String = it.baseAssetName
                }
            }

    override suspend fun getMarketCurrencies(base: Currency): List<Currency> =
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
