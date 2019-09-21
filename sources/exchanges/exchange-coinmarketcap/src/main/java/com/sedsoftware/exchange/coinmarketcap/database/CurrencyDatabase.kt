package com.sedsoftware.exchange.coinmarketcap.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sedsoftware.exchange.binance.database.converter.ThreeTenConverter
import com.sedsoftware.exchange.coinmarketcap.database.dao.CurrencyDao
import com.sedsoftware.exchange.coinmarketcap.database.dao.CurrencySyncInfoDao
import com.sedsoftware.exchange.coinmarketcap.database.model.CurrencyDbModel
import com.sedsoftware.exchange.coinmarketcap.database.model.CurrencySyncInfoDbModel

@Database(
    entities = [
        CurrencyDbModel::class,
        CurrencySyncInfoDbModel::class
    ],
    version = CurrencyDatabase.DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(
    ThreeTenConverter::class
)
abstract class CurrencyDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "currencies_db"
        const val DATABASE_VERSION = 1
    }

    abstract fun getCurrencyDao(): CurrencyDao
    abstract fun getCurrencySyncInfoDao(): CurrencySyncInfoDao
}
