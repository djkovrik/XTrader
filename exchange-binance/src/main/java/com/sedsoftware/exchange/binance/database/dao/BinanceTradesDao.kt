package com.sedsoftware.exchange.binance.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sedsoftware.exchange.binance.database.model.BinanceTradeDbModel

@Dao
abstract class BinanceTradesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(list: List<BinanceTradeDbModel>): List<Long>

    @Query("SELECT * FROM trades WHERE symbol LIKE :symbol")
    abstract fun getAll(symbol: String): List<BinanceTradeDbModel>

    @Query("DELETE FROM trades")
    abstract fun clearAll()
}
