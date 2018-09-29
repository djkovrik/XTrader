package com.sedsoftware.core.domain.repository.info

import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.core.domain.entity.info.CurrencyPairTrade
import com.sedsoftware.core.domain.repository.Repository
import com.sedsoftware.core.utils.common.Producer

interface PairTradesRepository : Repository {
    suspend fun getCurrencyPairTrades(pair: CurrencyPair): Producer<List<CurrencyPairTrade>>
}
