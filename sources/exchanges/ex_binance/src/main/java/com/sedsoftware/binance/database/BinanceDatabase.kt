package com.sedsoftware.binance.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sedsoftware.binance.database.converter.DateConverter
import com.sedsoftware.binance.database.converter.EnumsConverter
import com.sedsoftware.binance.database.converter.ListConverter
import com.sedsoftware.binance.database.dao.BinanceDepthsDao
import com.sedsoftware.binance.database.dao.BinanceSymbolsDao
import com.sedsoftware.binance.database.dao.BinanceTicksDao
import com.sedsoftware.binance.database.dao.BinanceTradesDao
import com.sedsoftware.binance.database.model.BinanceDepthDbModel
import com.sedsoftware.binance.database.model.BinanceSymbolDbModel
import com.sedsoftware.binance.database.model.BinanceTickDbModel
import com.sedsoftware.binance.database.model.BinanceTradeDbModel

@Database(
    entities = [
        BinanceDepthDbModel::class,
        BinanceSymbolDbModel::class,
        BinanceTickDbModel::class,
        BinanceTradeDbModel::class
    ],
    version = BinanceDatabase.DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(
    DateConverter::class,
    EnumsConverter::class,
    ListConverter::class
)
abstract class BinanceDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "binance_db"
        const val DATABASE_VERSION = 1
    }

    abstract fun getBinanceDepthsDao(): BinanceDepthsDao
    abstract fun getBinanceSymbolsDao(): BinanceSymbolsDao
    abstract fun getBinanceTicksDao(): BinanceTicksDao
    abstract fun getBinanceTradesDao(): BinanceTradesDao
}
