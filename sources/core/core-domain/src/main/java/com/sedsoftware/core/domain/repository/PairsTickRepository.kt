package com.sedsoftware.core.domain.repository

import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.core.domain.entity.CurrencyPairTick
import kotlinx.coroutines.flow.Flow

interface PairsTickRepository {
    suspend fun hasTicks(): Boolean
    suspend fun fetchPrice(pair: CurrencyPair): Float
    suspend fun saveActualPrice(pair: CurrencyPair, price: Float)
    suspend fun removePairFromWatchList(pair: CurrencyPair)
    suspend fun getCurrentWatchList(): List<CurrencyPairTick>
    suspend fun watchForTicks(): Flow<List<CurrencyPairTick>>
}
