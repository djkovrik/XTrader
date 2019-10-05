package com.sedsoftware.screens.intro.di

//@Component(
//    dependencies = [
//        MainActivityToolsProvider::class
//    ]
//)
//@Singleton
//interface IntroScreenComponent {
//
//    fun inject(fragment: IntroScreenFragment)
//
//    @Component.Factory
//    interface Factory {
//        fun create(@BindsInstance clickListener: ExchangeListAdapter.Listener,
//                   mainActivityToolsProvider: MainActivityToolsProvider
//        ): IntroScreenComponent
//    }
//
//    class Initializer private constructor() {
//        companion object {
//
//            fun init(clickListener: ExchangeListAdapter.Listener,
//                     mainActivityToolsProvider: MainActivityToolsProvider
//            ): IntroScreenComponent {
//
//                return DaggerIntroScreenComponent
//                    .factory()
//                    .create(clickListener, mainActivityToolsProvider)
//            }
//        }
//    }
//}
