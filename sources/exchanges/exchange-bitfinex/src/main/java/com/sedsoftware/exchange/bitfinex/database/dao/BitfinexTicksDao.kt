package com.sedsoftware.exchange.bitfinex.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sedsoftware.exchange.bitfinex.database.model.BitfinexPairTickDbModel
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.OffsetDateTime

@Dao
interface BitfinexTicksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: BitfinexPairTickDbModel): Long

    @Query("DELETE FROM bitfinex_ticks WHERE symbol_id LIKE :symbol")
    suspend fun delete(symbol: String)

    @Query("SELECT previous_price FROM bitfinex_ticks WHERE symbol_id LIKE :symbol")
    suspend fun getPreviousPrice(symbol: String): Float?

    @Query("SELECT insertion_date FROM bitfinex_ticks WHERE symbol_id LIKE :symbol")
    suspend fun getInsertionDate(symbol: String): OffsetDateTime?

    @Query("SELECT * FROM bitfinex_ticks LIMIT 1")
    suspend fun getFirstTick(): BitfinexPairTickDbModel?

    @Query("SELECT * FROM bitfinex_ticks WHERE symbol_id LIKE :symbol")
    suspend fun getTick(symbol: String): BitfinexPairTickDbModel

    @Query("SELECT * FROM bitfinex_ticks")
    suspend fun getTicks(): List<BitfinexPairTickDbModel>

    @Query("SELECT * FROM bitfinex_ticks")
    suspend fun getTicksFlow(): Flow<List<BitfinexPairTickDbModel>>
}
