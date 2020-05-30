package com.sedsoftware.exchange.binance.repository

import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.core.domain.entity.CurrencyPairTick
import com.sedsoftware.core.domain.repository.PairsTickRepository
import com.sedsoftware.exchange.binance.database.BinanceDatabase
import com.sedsoftware.exchange.binance.database.dao.BinanceTicksDao
import com.sedsoftware.exchange.binance.mapper.BinanceSymbolsMapper
import com.sedsoftware.exchange.binance.network.BinanceApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BinancePairsTickRepository @Inject constructor(
    private val api: BinanceApi,
    private val db: BinanceDatabase,
    private val mapper: BinanceSymbolsMapper
) : PairsTickRepository {

    private val dao: BinanceTicksDao by lazy {
        db.getBinanceTicksDao()
    }

    override suspend fun hasTicks(): Boolean =
        dao.getFirstTick() != null

    override suspend fun fetchTick(pair: CurrencyPair): CurrencyPairTick {
        TODO()
    }

    override suspend fun saveActualPrice(tick: CurrencyPairTick) {
        TODO()
    }

    override suspend fun addPairToWatchList(pair: CurrencyPair) {
        TODO()
    }

    override suspend fun removePairFromWatchList(pair: CurrencyPair) {
        TODO()
    }

    override suspend fun getCurrentWatchList(): List<CurrencyPairTick> {
        TODO()
    }

    override suspend fun watchForTicks(): Flow<List<CurrencyPairTick>> {
        TODO()
    }
}
