package com.sedsoftware.binance.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sedsoftware.binance.database.model.BinanceSymbolDbModel

@Dao
abstract class BinanceSymbolsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(list: List<BinanceSymbolDbModel>): List<Long>

    @Query("SELECT * FROM symbols")
    abstract fun getAllCurrencies(): List<BinanceSymbolDbModel>

    @Query("SELECT * FROM symbols WHERE base_asset LIKE :baseName")
    abstract fun getPairsForBase(baseName: String): List<BinanceSymbolDbModel>

    @Query("DELETE FROM symbols")
    abstract fun clearAll()
}
