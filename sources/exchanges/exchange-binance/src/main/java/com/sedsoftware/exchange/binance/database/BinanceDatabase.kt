package com.sedsoftware.exchange.binance.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sedsoftware.exchange.binance.database.converter.DateConverter
import com.sedsoftware.exchange.binance.database.converter.EnumsConverter
import com.sedsoftware.exchange.binance.database.converter.ListConverter
import com.sedsoftware.exchange.binance.database.dao.BinanceSymbolsDao
import com.sedsoftware.exchange.binance.database.model.BinanceSymbolDbModel

@Database(
    entities = [
        BinanceSymbolDbModel::class
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

    abstract fun getBinanceSymbolsDao(): BinanceSymbolsDao
}
