package com.sedsoftware.screens.main

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.sedsoftware.core.presentation.base.BaseViewModel
import com.sedsoftware.core.tools.api.Settings
import com.sedsoftware.screens.main.navigation.NavigationFlow
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
        private val settings: Settings
) : BaseViewModel() {

    internal var currentNavController = MutableLiveData<NavController>()
    internal var currentNavFlow = MutableLiveData<NavigationFlow>()

    internal val controller: NavController?
        get() = currentNavController.value

    internal val flow: NavigationFlow?
        get() = currentNavFlow.value

    internal val introNavGraph = R.navigation.navigation_intro

    internal val tabNavGraphs: List<Int> = listOf(
            R.navigation.navigation_wallet,
            R.navigation.navigation_orders,
            R.navigation.navigation_market,
            R.navigation.navigation_tracker,
            R.navigation.navigation_tools
    )

    init {
        if (settings.isExchangesDownloaded) {
            currentNavFlow.value = NavigationFlow.MAIN
        } else {
            currentNavFlow.value = NavigationFlow.INTRO
        }
    }
}
