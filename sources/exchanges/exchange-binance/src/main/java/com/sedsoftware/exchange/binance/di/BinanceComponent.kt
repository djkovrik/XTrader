package com.sedsoftware.exchange.binance.di

import com.sedsoftware.core.di.provider.BinanceProvider
import com.sedsoftware.core.di.provider.DeviceToolsProvider
import com.sedsoftware.exchange.binance.di.module.BinanceDatabaseModule
import com.sedsoftware.exchange.binance.di.module.BinanceExportModule
import com.sedsoftware.exchange.binance.di.module.BinanceNetworkModule
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        DeviceToolsProvider::class
    ],
    modules = [
        BinanceExportModule::class,
        BinanceDatabaseModule::class,
        BinanceNetworkModule::class
    ]
)
@Singleton
interface BinanceComponent : BinanceProvider {
    class Initializer private constructor() {
        companion object {

            fun init(deviceToolsProvider: DeviceToolsProvider): BinanceProvider {
                return DaggerBinanceComponent.builder()
                    .deviceToolsProvider(deviceToolsProvider)
                    .build()
            }
        }
    }
}
