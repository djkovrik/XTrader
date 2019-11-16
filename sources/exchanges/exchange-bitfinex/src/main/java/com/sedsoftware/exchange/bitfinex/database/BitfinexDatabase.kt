package com.sedsoftware.exchange.bitfinex.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sedsoftware.exchange.bitfinex.database.converter.ThreeTenConverter
import com.sedsoftware.exchange.bitfinex.database.dao.BitfinexSymbolsDao
import com.sedsoftware.exchange.bitfinex.database.dao.BitfinexSyncInfoDao
import com.sedsoftware.exchange.bitfinex.database.model.BitfinexSymbolDbModel
import com.sedsoftware.exchange.bitfinex.database.model.BitfinexSyncInfoDbModel

@Database(
    entities = [
        BitfinexSymbolDbModel::class,
        BitfinexSyncInfoDbModel::class
    ],
    version = BitfinexDatabase.DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(
    ThreeTenConverter::class
)
abstract class BitfinexDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "bitfinex_db"
        const val DATABASE_VERSION = 1
    }

    abstract fun getBitfinexSymbolsDao(): BitfinexSymbolsDao
    abstract fun getBitfinexSyncInfoDao(): BitfinexSyncInfoDao
}
