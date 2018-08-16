package com.sedsoftware.core.repository.info

import com.sedsoftware.core.common.Producer
import com.sedsoftware.core.entity.CurrencyPair
import com.sedsoftware.core.entity.info.CurrencyPairTick
import com.sedsoftware.core.repository.Repository

interface PairTickRepository : Repository {

    suspend fun getCurrencyTick(pair: CurrencyPair): Producer<CurrencyPairTick>
}
