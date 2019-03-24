package com.sedsoftware.exchange.binance.di.module

import androidx.room.Room
import com.sedsoftware.core.di.App
import com.sedsoftware.exchange.binance.database.BinanceDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BinanceDatabaseModule {

    @Provides
    @Singleton
    fun provideBinanceDatabase(app: App): BinanceDatabase =
        Room.databaseBuilder(app.getApplicationContext(), BinanceDatabase::class.java, BinanceDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
}
