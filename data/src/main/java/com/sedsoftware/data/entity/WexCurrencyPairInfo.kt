package com.sedsoftware.data.entity

import com.squareup.moshi.Json

data class WexCurrencyPairInfo(
  @Json(name = "decimal_places") val decimalPlaces: Int,
  @Json(name = "min_price") val minPrice: Float,
  @Json(name = "max_price") val maxPrice: Float,
  @Json(name = "min_amount") val minAmount: Float,
  @Json(name = "hidden") val hidden: Boolean,
  @Json(name = "fee") val fee: Float
)
