package com.sedsoftware.core.domain.repository

import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.core.domain.entity.CurrencyPairTick
import kotlinx.coroutines.flow.Flow

interface PairsTickRepository {
    suspend fun fetchActualPairTick(pair: CurrencyPair)
    suspend fun addPairToWatchList(pair: CurrencyPair)
    suspend fun removePairFromWatchList(pair: CurrencyPair)
    suspend fun watchForPair(pair: CurrencyPair): Flow<CurrencyPairTick>
}
