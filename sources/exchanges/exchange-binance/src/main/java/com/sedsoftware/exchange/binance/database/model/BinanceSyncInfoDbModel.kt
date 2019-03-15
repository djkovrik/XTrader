package com.sedsoftware.exchange.binance.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sync_info")
data class BinanceSyncInfoDbModel(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "last_sync_timestamp")
    val lastSyncDate: Long
)
