package com.sedsoftware.exchange.coinmarketcap.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime

@Entity(tableName = "coinmarketcap_sync_info")
data class CurrencySyncInfoDbModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = DEFAULT_ID,
    @ColumnInfo(name = "currency_count")
    val count: Int,
    @ColumnInfo(name = "last_sync_timestamp")
    val timestamp: OffsetDateTime
) {

    companion object {
        const val DEFAULT_ID = "coinmarketcap"
    }
}
