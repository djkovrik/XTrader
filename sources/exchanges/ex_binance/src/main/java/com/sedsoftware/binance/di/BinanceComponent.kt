package com.sedsoftware.binance.di

import com.sedsoftware.core.di.provider.DeviceToolsProvider
import com.sedsoftware.core.di.provider.exchange.BinanceProvider
import dagger.Component

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
