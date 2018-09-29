package com.sedsoftware.exchange.binance.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trades")
data class BinanceTradeDbModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "time")
    val time: Long,
    @ColumnInfo(name = "symbol")
    val symbol: String,
    @ColumnInfo(name = "quantity")
    val qty: Float,
    @ColumnInfo(name = "price")
    val price: Float,
    @ColumnInfo(name = "total")
    val total: Float,
    @ColumnInfo(name = "is_buyer_marker")
    val isBuyerMaker: Boolean,
    @ColumnInfo(name = "is_best_match")
    val isBestMatch: Boolean
)
