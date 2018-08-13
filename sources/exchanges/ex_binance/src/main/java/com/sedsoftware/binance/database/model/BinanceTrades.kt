package com.sedsoftware.binance.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "trades")
data class BinanceTrades(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "price")
    val price: Float,
    @ColumnInfo(name = "quantity")
    val qty: Float,
    @ColumnInfo(name = "time")
    val time: Date,
    @ColumnInfo(name = "is_buyer_marker")
    val isBuyerMaker: Boolean,
    @ColumnInfo(name = "is_best_match")
    val isBestMatch: Boolean
)