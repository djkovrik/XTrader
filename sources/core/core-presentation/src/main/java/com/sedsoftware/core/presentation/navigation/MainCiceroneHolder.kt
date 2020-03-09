package com.sedsoftware.core.presentation.navigation

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import java.util.EnumMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainCiceroneHolder @Inject constructor() {

    private val containers: EnumMap<AppFlow, Cicerone<Router>> =
        EnumMap<AppFlow, Cicerone<Router>>(
            AppFlow::class.java
        )

    fun getCicerone(flowKey: AppFlow): Cicerone<Router> {
        if (!containers.containsKey(flowKey)) {
            containers[flowKey] = Cicerone.create()
        }
        return containers[flowKey] ?: error("Failed to create Cicerone instance for $flowKey flow")
    }

    fun getRouter(flowKey: AppFlow): Router =
        getCicerone(flowKey).router

    fun getNavigatorHolder(flowKey: AppFlow): NavigatorHolder =
        getCicerone(flowKey).navigatorHolder
}
