package com.sedsoftware.exchange.bitfinex.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sedsoftware.exchange.bitfinex.database.model.BitfinexSyncInfoDbModel
import org.threeten.bp.OffsetDateTime

@Dao
interface BitfinexSyncInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: BitfinexSyncInfoDbModel): Long

    @Query("SELECT last_sync_date FROM bitfinex_sync_info")
    suspend fun getLastSyncDate(): OffsetDateTime?
}
