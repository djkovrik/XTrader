package com.sedsoftware.core.domain.repository.info

import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.core.domain.entity.info.CurrencyPairDepth
import com.sedsoftware.core.domain.repository.Repository
import com.sedsoftware.core.utils.common.Producer

interface PairDepthsRepository : Repository {
    suspend fun getCurrencyPairAsks(pair: CurrencyPair): Producer<List<CurrencyPairDepth>>
    suspend fun getCurrencyPairBids(pair: CurrencyPair): Producer<List<CurrencyPairDepth>>
}
