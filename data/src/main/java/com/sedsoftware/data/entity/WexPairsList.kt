package com.sedsoftware.data.entity

import com.squareup.moshi.Json

data class WexPairsList(
  @Json(name = "server_time") val serverTime: Long,
  @Json(name = "pairs") val pairs: Map<String, WexPairInfo>
)
