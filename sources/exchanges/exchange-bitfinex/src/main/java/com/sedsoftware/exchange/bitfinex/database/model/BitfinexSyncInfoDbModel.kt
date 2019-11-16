package com.sedsoftware.exchange.bitfinex.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime

@Entity(tableName = "bitfinex_sync_info")
data class BitfinexSyncInfoDbModel(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "last_sync_date")
    val lastSyncDate: OffsetDateTime
)
