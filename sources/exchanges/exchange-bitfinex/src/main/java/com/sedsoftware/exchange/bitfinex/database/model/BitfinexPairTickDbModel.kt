package com.sedsoftware.exchange.bitfinex.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime

@Entity(tableName = "bitfinex_ticks")
data class BitfinexPairTickDbModel(
    @PrimaryKey
    @ColumnInfo(name = "symbol_id")
    val symbol: String,
    @ColumnInfo(name = "base_currency_name")
    val baseCurrencyName: String,
    @ColumnInfo(name = "base_currency_label")
    val baseCurrencyLabel: String,
    @ColumnInfo(name = "quote_currency_name")
    val quoteCurrencyName: String,
    @ColumnInfo(name = "quote_currency_label")
    val quoteCurrencyLabel: String,
    @ColumnInfo(name = "previous_price")
    val previousPrice: Float,
    @ColumnInfo(name = "current_price")
    val currentPrice: Float,
    @ColumnInfo(name = "percent_change")
    val percentChange: Float,
    @ColumnInfo(name = "last_sync_date")
    val lastSyncDate: OffsetDateTime
)
