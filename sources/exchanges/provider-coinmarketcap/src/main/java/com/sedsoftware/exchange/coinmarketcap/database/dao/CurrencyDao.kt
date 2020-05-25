package com.sedsoftware.exchange.coinmarketcap.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sedsoftware.exchange.coinmarketcap.database.model.CurrencyDbModel

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<CurrencyDbModel>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CurrencyDbModel): Long

    @Query("SELECT * FROM coinmarketcap_currencies WHERE name_id LIKE :name")
    suspend fun getByName(name: String): CurrencyDbModel?

    @Query("DELETE FROM coinmarketcap_currencies")
    suspend fun clearAll()
}
