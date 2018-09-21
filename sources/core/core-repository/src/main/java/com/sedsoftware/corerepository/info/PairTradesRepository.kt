package com.sedsoftware.corerepository.info

import com.sedsoftware.coreentity.CurrencyPair
import com.sedsoftware.coreentity.info.CurrencyPairTrade
import com.sedsoftware.corerepository.Repository
import com.sedsoftware.coreutils.common.Producer

interface PairTradesRepository : Repository {

    suspend fun getCurrencyPairTrades(pair: CurrencyPair): Producer<List<CurrencyPairTrade>>
}
