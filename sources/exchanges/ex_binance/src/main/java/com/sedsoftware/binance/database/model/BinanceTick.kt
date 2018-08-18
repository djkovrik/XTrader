package com.sedsoftware.binance.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ticks")
data class BinanceTick(
    @PrimaryKey
    @ColumnInfo(name = "symbol_id")
    val symbol: String,
    @ColumnInfo(name = "bid_price")
    val bidPrice: Float,
    @ColumnInfo(name = "bid_quantity")
    val bidQty: Float,
    @ColumnInfo(name = "ask_price")
    val askPrice: Float,
    @ColumnInfo(name = "ask_quantity")
    val askQty: Float
)
