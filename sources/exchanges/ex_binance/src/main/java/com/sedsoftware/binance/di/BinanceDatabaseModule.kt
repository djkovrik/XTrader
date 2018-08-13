package com.sedsoftware.binance.di

import android.content.Context
import androidx.room.Room
import com.sedsoftware.binance.database.BinanceDatabase
import dagger.Module
import dagger.Provides

@Module
class BinanceDatabaseModule {

    @Provides
    fun provideBinanceDatabase(context: Context): BinanceDatabase =
        Room.databaseBuilder(context, BinanceDatabase::class.java, BinanceDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
}
