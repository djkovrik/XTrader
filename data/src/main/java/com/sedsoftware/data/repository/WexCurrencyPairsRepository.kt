package com.sedsoftware.data.repository

import com.sedsoftware.data.mapper.WexPairsMapper
import com.sedsoftware.data.network.WexApi
import com.sedsoftware.domain.entity.CurrencyPair
import com.sedsoftware.domain.repository.CurrencyPairsRepository
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import javax.inject.Inject

class WexCurrencyPairsRepository @Inject constructor(
  private val wexApi: WexApi,
  private val wexPairsMapper: WexPairsMapper
) : CurrencyPairsRepository {

  override suspend fun getCurrencyPairsList(): ReceiveChannel<List<CurrencyPair>> = produce {
    val pairsList = wexApi.loadCurrencyPairsList().await()
    send(wexPairsMapper.map(pairsList))
  }
}
