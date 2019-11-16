package com.sedsoftware.exchange.binance.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sedsoftware.exchange.binance.database.converter.EnumsConverter
import com.sedsoftware.exchange.binance.database.converter.ListConverter
import com.sedsoftware.exchange.binance.database.converter.ThreeTenConverter
import com.sedsoftware.exchange.binance.database.dao.BinanceSymbolsDao
import com.sedsoftware.exchange.binance.database.dao.BinanceSyncInfoDao
import com.sedsoftware.exchange.binance.database.model.BinanceSymbolDbModel
import com.sedsoftware.exchange.binance.database.model.BinanceSyncInfoDbModel

@Database(
    entities = [
        BinanceSymbolDbModel::class,
        BinanceSyncInfoDbModel::class
    ],
    version = BinanceDatabase.VERSION,
    exportSchema = false
)
@TypeConverters(
    ThreeTenConverter::class,
    EnumsConverter::class,
    ListConverter::class
)
abstract class BinanceDatabase : RoomDatabase() {

    companion object {
        const val NAME = "binance_db"
        const val VERSION = 1
    }

    abstract fun getBinanceSymbolsDao(): BinanceSymbolsDao
    abstract fun getBinanceSyncInfoDao(): BinanceSyncInfoDao
}
