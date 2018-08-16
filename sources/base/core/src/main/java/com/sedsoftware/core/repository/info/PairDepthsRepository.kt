package com.sedsoftware.core.repository.info

import com.sedsoftware.core.common.Producer
import com.sedsoftware.core.entity.CurrencyPair
import com.sedsoftware.core.entity.info.CurrencyPairDepth
import com.sedsoftware.core.repository.Repository

interface PairDepthsRepository : Repository {

    suspend fun getCurrencyPairAsks(pair: CurrencyPair): Producer<List<CurrencyPairDepth>>

    suspend fun getCurrencyPairBids(pair: CurrencyPair): Producer<List<CurrencyPairDepth>>
}
