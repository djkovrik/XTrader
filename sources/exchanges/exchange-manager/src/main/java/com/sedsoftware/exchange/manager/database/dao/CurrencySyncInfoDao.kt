package com.sedsoftware.exchange.manager.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sedsoftware.exchange.manager.database.model.CurrencySyncInfoDbModel

@Dao
interface CurrencySyncInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CurrencySyncInfoDbModel): Long

    @Query("DELETE FROM sync_info")
    suspend fun clearAll()
}
