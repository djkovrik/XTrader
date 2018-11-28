package com.sedsoftware.screens.splash.navigation

//@FragmentScope
//class RealSplashCoordinator @Inject constructor(
//    override val navControllerHolder: NavControllerHolder,
//    private val directionsFactory: NavDirectionsFactory,
//    private val settings: Settings
//) : SplashCoordinator {
//
//    override fun start() {
//        if (settings.isExchangesDownloaded) {
//            navigateToHome()
//        } else {
//            navigateToIntro()
//        }
//    }
//
//    override fun navigateToHome() {
//        navControllerHolder.getNavController().navigate(directionsFactory.create(NavRoutes.Splash.ToHome))
//    }
//
//    override fun navigateToIntro() {
//        navControllerHolder.getNavController().navigate(directionsFactory.create(NavRoutes.Splash.ToIntro))
//    }
//
//    override fun navigateToPin() {
//        // TODO
//    }
//}
