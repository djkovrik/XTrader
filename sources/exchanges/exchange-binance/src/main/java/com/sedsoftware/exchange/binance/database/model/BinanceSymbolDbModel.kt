package com.sedsoftware.exchange.binance.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sedsoftware.exchange.binance.common.params.OrderType
import com.sedsoftware.exchange.binance.common.params.SymbolStatus
import com.sedsoftware.exchange.binance.network.model.common.Filter

@Entity(tableName = "symbols")
data class BinanceSymbolDbModel(
    @PrimaryKey
    @ColumnInfo(name = "symbol_id")
    val symbol: String,
    @ColumnInfo(name = "last_sync_timestamp")
    val syncDate: Long,
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
    val icebergAllowed: Boolean,
    @ColumnInfo(name = "filters")
    val filters: List<Filter>
)
