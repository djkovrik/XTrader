package com.sedsoftware.domain.entity

data class CurrencyPairInfo(
  val name: String,
  val first: String,
  val second: String,
  val decimalPlaces: Int,
  val minPrice: Float,
  val maxPrice: Float,
  val minAmount: Float,
  val hidden: Boolean,
  val fee: Float
)
