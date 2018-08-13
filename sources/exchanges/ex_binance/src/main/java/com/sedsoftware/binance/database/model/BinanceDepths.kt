package com.sedsoftware.binance.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sedsoftware.binance.common.enums.OrderType

@Entity(tableName = "depths")
data class BinanceDepths(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "base_currency_name")
    val baseCurrencyName: String,
    @ColumnInfo(name = "base_currency_symbol")
    val baseCurrencySymbol: String,
    @ColumnInfo(name = "market_currency_name")
    val marketCurrencyName: String,
    @ColumnInfo(name = "market_currency_symbol")
    val marketCurrencySymbol: String,
    @ColumnInfo(name = "order_type")
    val orderType: OrderType
)
