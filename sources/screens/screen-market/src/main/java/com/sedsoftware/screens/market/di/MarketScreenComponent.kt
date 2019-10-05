package com.sedsoftware.screens.market.di

//@Component(
//    dependencies = [
//        MainActivityToolsProvider::class
//    ]
//)
//@Singleton
//interface MarketScreenComponent {
//
//    fun inject(fragment: MarketScreenFragment)
//
//    @Component.Factory
//    interface Factory {
//        fun create(@BindsInstance clickListener: CurrencyListAdapter.Listener,
//                   mainActivityToolsProvider: MainActivityToolsProvider
//        ): MarketScreenComponent
//    }
//
//    class Initializer private constructor() {
//        companion object {
//
//            fun init(clickListener: CurrencyListAdapter.Listener,
//                     mainActivityToolsProvider: MainActivityToolsProvider
//            ): MarketScreenComponent {
//
//                return DaggerMarketScreenComponent
//                    .factory()
//                    .create(clickListener, mainActivityToolsProvider)
//            }
//        }
//    }
//}
