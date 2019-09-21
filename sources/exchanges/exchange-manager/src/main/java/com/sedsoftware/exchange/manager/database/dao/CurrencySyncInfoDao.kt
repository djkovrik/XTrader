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

    @Query("SELECT currency_count FROM currency_sync_info WHERE id LIKE ${CurrencySyncInfoDbModel.DEFAULT_ID}")
    suspend fun getSavedCurrencyCount(): Int?

    @Query("DELETE FROM sync_info")
    suspend fun clearAll()
}
