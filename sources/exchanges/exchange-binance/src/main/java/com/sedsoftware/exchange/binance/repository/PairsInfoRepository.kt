package com.sedsoftware.exchange.binance.repository

import com.sedsoftware.core.tools.api.Settings
import com.sedsoftware.core.utils.type.Either
import com.sedsoftware.core.utils.type.Either.Left
import com.sedsoftware.core.utils.type.Either.Right
import com.sedsoftware.core.utils.type.Failure
import com.sedsoftware.core.utils.type.Failure.LocalPersistenceError
import com.sedsoftware.core.utils.type.Success
import com.sedsoftware.core.utils.type.Success.PairsLoadingCompleted
import com.sedsoftware.core.utils.network.safeApiCall
import com.sedsoftware.exchange.binance.database.BinanceDatabase
import com.sedsoftware.exchange.binance.database.dao.BinanceSymbolsDao
import com.sedsoftware.exchange.binance.database.dao.BinanceSyncInfoDao
import com.sedsoftware.exchange.binance.mapper.BinanceSymbolsInfoMapper
import com.sedsoftware.exchange.binance.network.BinanceApi
import com.sedsoftware.exchange.binance.network.model.PairsInfo
import javax.inject.Inject

class PairsInfoRepository @Inject constructor(
    private val api: BinanceApi,
    private val settings: Settings,
    private val db: BinanceDatabase,
    private val mapper: BinanceSymbolsInfoMapper
) {

    private val symbolsDao: BinanceSymbolsDao by lazy {
        db.getBinanceSymbolsDao()
    }

    private val syncInfoDao: BinanceSyncInfoDao by lazy {
        db.getBinanceSyncInfoDao()
    }

    suspend fun getRemotePairsInfo(): Either<Failure, Success> =
        when (val result = safeApiCall { api.getCurrencyPairs() }) {
            is Left -> Left(result.a)
            is Right -> {
                try {
                    storePairsInfo(result.b)
                    storeSyncInfo(result.b)
                    markAsDownloaded()
                } catch (exception: Exception) {
                    Left(LocalPersistenceError(exception))
                }
                Right(PairsLoadingCompleted)
            }
        }

    private suspend fun storePairsInfo(info: PairsInfo) {
        symbolsDao.clearAll()
        symbolsDao.insert(mapper.mapSymbolsToDb(info))
    }

    private suspend fun storeSyncInfo(info: PairsInfo) {
        syncInfoDao.insert(mapper.mapSyncInfoToDb(info))
    }

    private fun markAsDownloaded() {
        if (!settings.isExchangesDownloaded) {
            settings.isExchangesDownloaded = true
        }
    }
}
