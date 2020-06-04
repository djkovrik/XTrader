package com.sedsoftware.exchange.binance.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sedsoftware.exchange.binance.database.model.BinancePairTickDbModel
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.OffsetDateTime

@Dao
interface BinanceTicksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: BinancePairTickDbModel): Long

    @Query("DELETE FROM binance_ticks WHERE symbol_id LIKE :symbol")
    suspend fun delete(symbol: String)

    @Query("SELECT previous_price FROM binance_ticks WHERE symbol_id LIKE :symbol")
    suspend fun getPreviousPrice(symbol: String): Float?

    @Query("SELECT insertion_date FROM binance_ticks WHERE symbol_id LIKE :symbol")
    suspend fun getInsertionDate(symbol: String): OffsetDateTime?

    @Query("SELECT * FROM binance_ticks LIMIT 1")
    suspend fun getFirstTick(): BinancePairTickDbModel?

    @Query("SELECT * FROM binance_ticks WHERE symbol_id LIKE :symbol")
    suspend fun getTick(symbol: String): BinancePairTickDbModel

    @Query("SELECT * FROM binance_ticks")
    suspend fun getTicks(): List<BinancePairTickDbModel>

    @Query("SELECT * FROM binance_ticks")
    suspend fun getTicksFlow(): Flow<List<BinancePairTickDbModel>>
}
