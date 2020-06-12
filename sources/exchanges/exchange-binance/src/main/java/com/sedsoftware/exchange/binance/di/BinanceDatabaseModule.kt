package com.sedsoftware.exchange.binance.di

import android.content.Context
import androidx.room.Room
import com.sedsoftware.exchange.binance.database.BinanceDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
object BinanceDatabaseModule {

    @Provides
    fun provideBinanceDatabase(@ApplicationContext context: Context): BinanceDatabase =
        Room.databaseBuilder(context, BinanceDatabase::class.java, BinanceDatabase.NAME)
            .fallbackToDestructiveMigration()
            .build()
}
