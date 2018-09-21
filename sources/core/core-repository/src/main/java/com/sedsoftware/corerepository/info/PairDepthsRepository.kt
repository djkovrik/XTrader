package com.sedsoftware.corerepository.info

import com.sedsoftware.coreentity.CurrencyPair
import com.sedsoftware.coreentity.info.CurrencyPairDepth
import com.sedsoftware.corerepository.Repository
import com.sedsoftware.coreutils.common.Producer

interface PairDepthsRepository : Repository {

    suspend fun getCurrencyPairAsks(pair: CurrencyPair): Producer<List<CurrencyPairDepth>>

    suspend fun getCurrencyPairBids(pair: CurrencyPair): Producer<List<CurrencyPairDepth>>
}
