package com.sedsoftware.coreapi.repository.info

import com.sedsoftware.coreapi.entity.CurrencyPair
import com.sedsoftware.coreapi.entity.info.CurrencyPairTrade
import com.sedsoftware.coreapi.repository.Repository
import com.sedsoftware.coreutils.common.Producer

interface PairTradesRepository : Repository {

    suspend fun getCurrencyPairTrades(pair: CurrencyPair): Producer<List<CurrencyPairTrade>>
}
