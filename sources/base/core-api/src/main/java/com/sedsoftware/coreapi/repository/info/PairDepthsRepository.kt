package com.sedsoftware.coreapi.repository.info

import com.sedsoftware.coreapi.entity.CurrencyPair
import com.sedsoftware.coreapi.entity.info.CurrencyPairDepth
import com.sedsoftware.coreapi.repository.Repository
import com.sedsoftware.coreutils.common.Producer

interface PairDepthsRepository : Repository {

    suspend fun getCurrencyPairAsks(pair: CurrencyPair): Producer<List<CurrencyPairDepth>>

    suspend fun getCurrencyPairBids(pair: CurrencyPair): Producer<List<CurrencyPairDepth>>
}
