package com.sedsoftware.binance.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sedsoftware.binance.database.model.BinanceDepthDbModel

@Dao
abstract class BinanceDepthsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(list: List<BinanceDepthDbModel>): List<Long>

    @Query("SELECT * FROM depths WHERE symbol LIKE :symbol")
    abstract fun getAll(symbol: String): List<BinanceDepthDbModel>

    @Query("DELETE FROM depths")
    abstract fun clearAll()
}
