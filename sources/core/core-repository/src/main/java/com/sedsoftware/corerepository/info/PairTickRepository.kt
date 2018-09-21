package com.sedsoftware.corerepository.info

import com.sedsoftware.coreentity.CurrencyPair
import com.sedsoftware.coreentity.info.CurrencyPairTick
import com.sedsoftware.corerepository.Repository
import com.sedsoftware.coreutils.common.Producer

interface PairTickRepository : Repository {

    suspend fun getCurrencyTick(pair: CurrencyPair): Producer<CurrencyPairTick>
}
