package com.sedsoftware.exchange.bitfinex.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sedsoftware.exchange.bitfinex.database.model.BitfinexSymbolDbModel

@Dao
interface BitfinexSymbolsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<BitfinexSymbolDbModel>): List<Long>

    @Query("SELECT * FROM bitfinex_symbols")
    suspend fun getBaseCurrencies(): List<BitfinexSymbolDbModel>

    @Query("SELECT * FROM bitfinex_symbols WHERE base_asset LIKE :baseName")
    suspend fun getCurrenciesForBase(baseName: String): List<BitfinexSymbolDbModel>

    @Query("DELETE FROM bitfinex_symbols")
    suspend fun clearAll()
}
