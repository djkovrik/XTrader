package com.sedsoftware.exchange.coinmarketcap.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coinmarketcap_currencies")
data class CurrencyDbModel(
    @PrimaryKey
    @ColumnInfo(name = "name_id")
    val name: String,
    @ColumnInfo(name = "label")
    val label: String
)
