package com.sedsoftware.binance.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sedsoftware.binance.enums.OrderType
import com.sedsoftware.binance.enums.SymbolStatus

@Entity(tableName = "symbols")
data class BinanceSymbol(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "symbol")
    val symbol: String,
    @ColumnInfo(name = "status")
    val status: SymbolStatus,
    @ColumnInfo(name = "base_asset")
    val baseAsset: String,
    @ColumnInfo(name = "base_asset_precision")
    val baseAssetPrecision: Int,
    @ColumnInfo(name = "quote_asset")
    val quoteAsset: String,
    @ColumnInfo(name = "quote_precision")
    val quotePrecision: Int,
    @ColumnInfo(name = "order_types")
    val orderTypes: List<OrderType>,
    @ColumnInfo(name = "iceberg_allowed")
    val icebergAllowed: Boolean
)
