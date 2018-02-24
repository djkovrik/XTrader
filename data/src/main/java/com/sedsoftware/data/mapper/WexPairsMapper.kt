package com.sedsoftware.data.mapper

import com.sedsoftware.data.entity.WexCurrencyPairsList
import com.sedsoftware.domain.entity.CurrencyPair
import javax.inject.Inject

class WexPairsMapper @Inject constructor() {

  fun map(from: WexCurrencyPairsList): List<CurrencyPair> =
    from.pairs.map { (pairName, pairInfo) ->
      CurrencyPair(
        name = pairName,
        first = pairName.substringBefore("_"),
        second = pairName.substringAfter("_"),
        decimalPlaces = pairInfo.decimalPlaces,
        minPrice = pairInfo.minPrice,
        maxPrice = pairInfo.maxPrice,
        minAmount = pairInfo.minAmount,
        hidden = pairInfo.hidden == 0,
        fee = pairInfo.fee
      )
    }
}
