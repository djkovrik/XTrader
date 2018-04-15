package com.sedsoftware.data.mapper

import com.sedsoftware.data.db.WexPairInfoDbModel
import com.sedsoftware.data.entity.WexPairsList
import com.sedsoftware.domain.entity.CurrencyPairInfo
import javax.inject.Inject

class WexPairDataMapper @Inject constructor() {

  fun mapDataToDb(from: WexPairsList): List<WexPairInfoDbModel> =
    from.pairs.map { (pairName, pairInfo) ->
      WexPairInfoDbModel(
        name = pairName,
        first = pairName.substringBefore("_"),
        second = pairName.substringAfter("_"),
        decimalPlaces = pairInfo.decimalPlaces,
        minPrice = pairInfo.minPrice,
        maxPrice = pairInfo.maxPrice,
        minAmount = pairInfo.minAmount,
        hidden = pairInfo.hidden == 1,
        fee = pairInfo.fee
      )
    }

  fun mapDbToDomain(from: WexPairInfoDbModel) =
    CurrencyPairInfo(
      name = from.name,
      first = from.first,
      second = from.second,
      decimalPlaces = from.decimalPlaces,
      minPrice = from.minPrice,
      maxPrice = from.maxPrice,
      minAmount = from.minAmount,
      hidden = from.hidden,
      fee = from.fee
    )
}
