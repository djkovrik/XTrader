package com.sedsoftware.exchange.bitfinex.di

import android.content.Context
import androidx.room.Room
import com.sedsoftware.exchange.bitfinex.database.BitfinexDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
object BitfinexDatabaseModule {

    @Provides
    fun provideBinanceDatabase(@ApplicationContext context: Context): BitfinexDatabase =
        Room.databaseBuilder(context, BitfinexDatabase::class.java, BitfinexDatabase.NAME)
            .fallbackToDestructiveMigration()
            .build()
}
