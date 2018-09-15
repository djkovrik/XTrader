package com.sedsoftware.binance.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sedsoftware.binance.database.model.BinanceTickDbModel

@Dao
abstract class BinanceTicksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(item: BinanceTickDbModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(list: List<BinanceTickDbModel>): List<Long>

    @Query("SELECT * FROM ticks WHERE symbol_id LIKE :symbol")
    abstract fun getOne(symbol: String): List<BinanceTickDbModel>

    @Query("SELECT * FROM ticks")
    abstract fun getAll(): List<BinanceTickDbModel>

    @Query("DELETE FROM ticks")
    abstract fun clearAll()
}
