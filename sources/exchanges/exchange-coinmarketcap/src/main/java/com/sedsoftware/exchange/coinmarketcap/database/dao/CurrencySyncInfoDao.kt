package com.sedsoftware.exchange.coinmarketcap.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sedsoftware.exchange.coinmarketcap.database.model.CurrencySyncInfoDbModel

@Dao
interface CurrencySyncInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CurrencySyncInfoDbModel): Long

    @Query("SELECT currency_count FROM coinmarketcap_sync_info WHERE id LIKE '${CurrencySyncInfoDbModel.DEFAULT_ID}'")
    suspend fun getSavedCurrencyCount(): Int?

    @Query("DELETE FROM coinmarketcap_sync_info")
    suspend fun clearAll()
}
