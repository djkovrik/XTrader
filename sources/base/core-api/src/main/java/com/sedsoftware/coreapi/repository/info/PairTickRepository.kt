package com.sedsoftware.coreapi.repository.info

import com.sedsoftware.coreapi.entity.CurrencyPair
import com.sedsoftware.coreapi.entity.info.CurrencyPairTick
import com.sedsoftware.coreapi.repository.Repository
import com.sedsoftware.coreutils.common.Producer

interface PairTickRepository : Repository {

    suspend fun getCurrencyTick(pair: CurrencyPair): Producer<CurrencyPairTick>
}
