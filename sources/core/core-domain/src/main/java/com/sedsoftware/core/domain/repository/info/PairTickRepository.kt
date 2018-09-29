package com.sedsoftware.core.domain.repository.info

import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.core.domain.entity.info.CurrencyPairTick
import com.sedsoftware.core.domain.repository.Repository
import com.sedsoftware.core.utils.common.Producer

interface PairTickRepository : Repository {
    suspend fun getCurrencyTick(pair: CurrencyPair): Producer<CurrencyPairTick>
}
