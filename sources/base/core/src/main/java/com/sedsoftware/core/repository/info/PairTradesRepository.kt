package com.sedsoftware.core.repository.info

import com.sedsoftware.core.common.Producer
import com.sedsoftware.core.entity.CurrencyPair
import com.sedsoftware.core.entity.info.CurrencyPairTrade
import com.sedsoftware.core.repository.Repository

interface PairTradesRepository : Repository {

    suspend fun getCurrencyPairTrades(pair: CurrencyPair): Producer<List<CurrencyPairTrade>>
}
