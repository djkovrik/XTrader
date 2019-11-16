package com.sedsoftware.exchange.bitfinex.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bitfinex_symbols")
data class BitfinexSymbolDbModel(
    @PrimaryKey
    @ColumnInfo(name = "symbol_id")
    val symbol: String,
    @ColumnInfo(name = "base_asset")
    val baseAsset: String,
    @ColumnInfo(name = "base_asset_name")
    val baseAssetName: String,
    @ColumnInfo(name = "quote_asset")
    val quoteAsset: String,
    @ColumnInfo(name = "quote_asset_name")
    val quoteAssetName: String
)
