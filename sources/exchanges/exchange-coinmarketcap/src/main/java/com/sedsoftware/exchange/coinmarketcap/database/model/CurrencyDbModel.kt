package com.sedsoftware.exchange.coinmarketcap.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coinmarketcap_currencies")
data class CurrencyDbModel(
    @PrimaryKey
    @ColumnInfo(name = "symbol_id")
    val symbol: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "is_active")
    val isActive: Int
)
