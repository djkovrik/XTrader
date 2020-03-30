package com.sedsoftware.core.domain.tools

import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

interface CiceroneManager {
    fun getRouter(key: String): Router
    fun getNavigatorHolder(key: String): NavigatorHolder
}
