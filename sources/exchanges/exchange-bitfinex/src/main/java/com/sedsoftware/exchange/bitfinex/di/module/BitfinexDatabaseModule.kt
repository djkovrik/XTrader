package com.sedsoftware.exchange.bitfinex.di.module

import androidx.room.Room
import com.sedsoftware.core.di.App
import com.sedsoftware.exchange.bitfinex.database.BitfinexDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object BitfinexDatabaseModule {

    @Provides
    @Singleton
    fun provideBitfinexDatabase(app: App): BitfinexDatabase =
        Room.databaseBuilder(app.getApplicationContext(), BitfinexDatabase::class.java, BitfinexDatabase.NAME)
            .fallbackToDestructiveMigration()
            .build()
}
