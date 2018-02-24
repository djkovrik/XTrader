package com.sedsoftware.domain.repository

import com.sedsoftware.domain.entity.CurrencyPair
import kotlinx.coroutines.experimental.channels.ReceiveChannel

interface CurrencyPairsRepository : Repository {

  suspend fun getCurrencyPairsList(): ReceiveChannel<List<CurrencyPair>>
}
