package com.sedsoftware.binance.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sedsoftware.binance.common.params.OrderSide

@Entity(tableName = "depths")
data class BinanceDepthDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "base_currency_name")
    val baseCurrencyName: String,
    @ColumnInfo(name = "base_currency_label")
    val baseCurrencyLabel: String,
    @ColumnInfo(name = "market_currency_name")
    val marketCurrencyName: String,
    @ColumnInfo(name = "market_currency_label")
    val marketCurrencyLabel: String,
    @ColumnInfo(name = "amount")
    val amount: Float,
    @ColumnInfo(name = "price")
    val price: Float,
    @ColumnInfo(name = "total")
    val total: Float,
    @ColumnInfo(name = "order_side")
    val orderSide: OrderSide
)
