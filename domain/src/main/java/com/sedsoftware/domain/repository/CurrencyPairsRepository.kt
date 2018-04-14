package com.sedsoftware.domain.repository

import com.sedsoftware.domain.entity.CurrencyPairInfo
import kotlinx.coroutines.experimental.channels.ReceiveChannel

interface CurrencyPairsRepository : Repository {

  suspend fun getCurrencyPairsList(): ReceiveChannel<List<CurrencyPairInfo>>
}
