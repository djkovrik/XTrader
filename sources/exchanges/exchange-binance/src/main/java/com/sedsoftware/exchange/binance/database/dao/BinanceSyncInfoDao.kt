package com.sedsoftware.exchange.binance.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sedsoftware.exchange.binance.database.model.BinanceSyncInfoDbModel
import org.threeten.bp.OffsetDateTime

@Dao
interface BinanceSyncInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: BinanceSyncInfoDbModel): Long

    @Query("SELECT last_sync_date FROM binance_sync_info")
    suspend fun getLastSyncDate(): OffsetDateTime?
}
