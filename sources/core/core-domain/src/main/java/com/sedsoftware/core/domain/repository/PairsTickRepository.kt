package com.sedsoftware.core.domain.repository

import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.core.domain.entity.CurrencyPairTick
import kotlinx.coroutines.flow.Flow

interface PairsTickRepository {
    suspend fun hasTicks(): Boolean
    suspend fun fetchTick(pair: CurrencyPair): CurrencyPairTick
    suspend fun saveActualPrice(tick: CurrencyPairTick)
    suspend fun addPairToWatchList(pair: CurrencyPair)
    suspend fun removePairFromWatchList(pair: CurrencyPair)
    suspend fun getCurrentWatchList(): List<CurrencyPairTick>
    suspend fun watchForTicks(): Flow<List<CurrencyPairTick>>
}
