package com.sedsoftware.screens.main

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.sedsoftware.core.presentation.base.BaseViewModel
import com.sedsoftware.core.tools.api.Settings
import com.sedsoftware.screens.main.navigation.NavigationFlow
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(settings: Settings) : BaseViewModel() {

    companion object {

        internal val introNavGraph = R.navigation.navigation_intro

        internal val mainNavGraphs: List<Int> = listOf(
            R.navigation.navigation_wallet,
            R.navigation.navigation_orders,
            R.navigation.navigation_market,
            R.navigation.navigation_tracker,
            R.navigation.navigation_tools
        )
    }

    internal var navControllerLiveData = MutableLiveData<NavController>()
    internal var navFlowLiveData = MutableLiveData<NavigationFlow>()

    init {
        navFlowLiveData.value = when {
            settings.isExchangesDownloaded -> NavigationFlow.MAIN
            else -> NavigationFlow.INTRO
        }
    }
}
