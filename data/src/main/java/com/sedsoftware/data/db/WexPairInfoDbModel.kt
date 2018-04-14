package com.sedsoftware.data.db

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

data class WexPairInfoDbModel(
  @PrimaryKey
  val id: Int = 0,
  val name: String,
  val first: String,
  val second: String,
  val decimalPlaces: Int,
  val minPrice: Float,
  val maxPrice: Float,
  val minAmount: Float,
  val hidden: Boolean,
  val fee: Float
) : RealmObject()
