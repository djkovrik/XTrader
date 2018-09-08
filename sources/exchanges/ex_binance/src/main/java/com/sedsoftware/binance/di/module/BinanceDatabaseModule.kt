package com.sedsoftware.binance.di.module

import android.content.Context
import androidx.room.Room
import com.sedsoftware.binance.database.BinanceDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BinanceDatabaseModule {

    @Provides
    @Singleton
    fun provideBinanceDatabase(context: Context): BinanceDatabase =
        Room.databaseBuilder(context, BinanceDatabase::class.java, BinanceDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
}
