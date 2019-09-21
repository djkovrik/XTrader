package com.sedsoftware.exchange.manager.di.module

import androidx.room.Room
import com.sedsoftware.core.di.App
import com.sedsoftware.exchange.manager.database.CurrencyDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CurrencyDatabaseModule {

    @Provides
    @Singleton
    fun provideCurrencyDatabase(app: App): CurrencyDatabase =
        Room.databaseBuilder(app.getApplicationContext(), CurrencyDatabase::class.java, CurrencyDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
}
