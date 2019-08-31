package com.sedsoftware.exchange.binance.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sedsoftware.exchange.binance.database.model.BinanceSymbolDbModel

@Dao
interface BinanceSymbolsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<BinanceSymbolDbModel>): List<Long>

    @Query("SELECT * FROM symbols")
    suspend fun getBaseCurrencies(): List<BinanceSymbolDbModel>

    @Query("SELECT * FROM symbols WHERE base_asset LIKE :baseName")
    suspend fun getCurrenciesForBase(baseName: String): List<BinanceSymbolDbModel>

    @Query("DELETE FROM symbols")
    suspend fun clearAll()
}
