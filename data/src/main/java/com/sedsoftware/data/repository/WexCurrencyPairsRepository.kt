package com.sedsoftware.data.repository

import com.sedsoftware.data.mapper.WexPairDataMapper
import com.sedsoftware.data.network.WexApi
import com.sedsoftware.domain.entity.CurrencyPairInfo
import com.sedsoftware.domain.repository.CurrencyPairsRepository
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import javax.inject.Inject

class WexCurrencyPairsRepository @Inject constructor(
  private val wexApi: WexApi,
  private val wexPairsMapper: WexPairDataMapper
) : CurrencyPairsRepository {

  override suspend fun getCurrencyPairsList(): ReceiveChannel<List<CurrencyPairInfo>> = produce {
    val pairsList = wexApi.loadCurrencyPairsList().await()
    val mappedList = wexPairsMapper.mapDataToDb(pairsList)
//    send(wexPairsMapper.mapToDomain(pairsList))
  }
}
