package com.sedsoftware.exchange.binance.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sedsoftware.exchange.binance.database.model.BinancePairTickDbModel

@Dao
interface BinanceTicksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: BinancePairTickDbModel): Long

    @Query("DELETE FROM binance_ticks WHERE symbol_id LIKE :symbol")
    suspend fun delete(symbol: String)

    @Query("SELECT previous_price FROM binance_ticks WHERE symbol_id LIKE :symbol")
    suspend fun getPreviousPrice(symbol: String): Float

    @Query("SELECT * FROM binance_ticks WHERE symbol_id LIKE :symbol")
    suspend fun watchForTick(symbol: String): BinancePairTickDbModel
}
