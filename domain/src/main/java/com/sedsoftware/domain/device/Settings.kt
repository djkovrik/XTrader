package com.sedsoftware.domain.device

interface Settings {

  fun setPairsLastSyncTimestamp(timestamp: Long)

  fun getPairsLastSyncTimestamp(): Long
}
