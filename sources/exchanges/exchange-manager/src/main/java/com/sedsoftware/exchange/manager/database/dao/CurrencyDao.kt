package com.sedsoftware.exchange.manager.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sedsoftware.exchange.manager.database.model.CurrencyDbModel
import com.sedsoftware.exchange.manager.database.model.CurrencySyncInfoDbModel

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<CurrencyDbModel>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CurrencyDbModel): Long

    @Query("SELECT * FROM currencies WHERE symbol_id LIKE :symbol")
    suspend fun getBySymbol(symbol: String): CurrencyDbModel

    @Query("DELETE FROM currencies")
    suspend fun clearAll()
}
