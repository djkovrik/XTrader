package com.sedsoftware.wexchanger.presentation.navigation

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class LocalNavigatorHolder(private val containers: MutableMap<String, Cicerone<Router>> = hashMapOf()) {

  fun getCicerone(containerTag: String): Cicerone<Router> =
    containers.getOrPut(containerTag, { Cicerone.create() })
}
