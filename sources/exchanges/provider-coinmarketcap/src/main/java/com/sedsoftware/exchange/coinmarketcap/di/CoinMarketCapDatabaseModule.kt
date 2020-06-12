package com.sedsoftware.exchange.coinmarketcap.di

import android.content.Context
import androidx.room.Room
import com.sedsoftware.exchange.coinmarketcap.database.CurrencyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
object CoinMarketCapDatabaseModule {

    @Provides
    fun provideCurrencyDatabase(@ApplicationContext context: Context): CurrencyDatabase =
        Room.databaseBuilder(context, CurrencyDatabase::class.java, CurrencyDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
}
