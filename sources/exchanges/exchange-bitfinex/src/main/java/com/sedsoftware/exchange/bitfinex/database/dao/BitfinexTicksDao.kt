package com.sedsoftware.exchange.bitfinex.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sedsoftware.exchange.bitfinex.database.model.BitfinexPairTickDbModel

@Dao
interface BitfinexTicksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: BitfinexPairTickDbModel): Long

    @Query("DELETE FROM bitfinex_ticks WHERE symbol_id LIKE :symbol")
    suspend fun delete(symbol: String)

    @Query("SELECT previous_price FROM bitfinex_ticks WHERE symbol_id LIKE :symbol")
    suspend fun getPreviousPrice(symbol: String): Float

    @Query("SELECT * FROM bitfinex_ticks WHERE symbol_id LIKE :symbol")
    suspend fun watchForTick(symbol: String): BitfinexPairTickDbModel
}
