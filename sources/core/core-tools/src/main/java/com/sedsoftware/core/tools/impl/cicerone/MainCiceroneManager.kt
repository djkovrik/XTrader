package com.sedsoftware.core.tools.impl.cicerone

import com.sedsoftware.core.domain.tools.CiceroneManager
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainCiceroneManager @Inject constructor() :
    CiceroneManager {

    private val containers: HashMap<String, Cicerone<Router>> = HashMap()

    override fun getRouter(key: String): Router =
        getCicerone(key).router

    override fun getNavigatorHolder(key: String): NavigatorHolder =
        getCicerone(key).navigatorHolder

    private fun getCicerone(key: String): Cicerone<Router> {
        if (!containers.containsKey(key)) {
            containers[key] = Cicerone.create()
        }
        return containers[key] ?: error("Failed to create Cicerone instance for $key flow")
    }
}
