package com.sedsoftware.screens.intro.base

//
//class IntroBaseViewModel @Inject constructor(
//    private val coordinator: StartingFlowCoordinator,
//    private val currencyMapLoader: CurrencyMapLoader
//) : BaseViewModel() {
//
//    internal val downloadCompletedLiveData = MutableLiveData<Boolean>(false)
//
//    init {
//        launch {
//            currencyMapLoader
//                .isLoadingNeeded()
//                .either(::handleFailure, ::handleMapCheck)
//        }
//    }
//
//    fun downloadCurrencyMap() {
//        launch {
//            currencyMapLoader
//                .loadCurrencyMap()
//                .either(::handleFailure, ::handleSuccess)
//        }
//    }
//
//    fun navigateToExchanges() {
//        coordinator.navigateToExchangeScreen()
//    }
//
//    private fun handleSuccess(downloadSuccess: Success) {
//        if (downloadSuccess is CurrencyMapLoadingCompleted) {
//            downloadCompletedLiveData.value = true
//        }
//    }
//
//    private fun handleMapCheck(loadingNeeded: Boolean) {
//        if (!loadingNeeded) {
//            navigateToExchanges()
//        }
//    }
//}
