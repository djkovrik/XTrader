package com.sedsoftware.exchange.manager.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime

@Entity(tableName = "sync_info")
data class CurrencySyncInfoDbModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "currency_count")
    val count: Int,
    @ColumnInfo(name = "last_sync_timestamp")
    val timestamp: OffsetDateTime
)
