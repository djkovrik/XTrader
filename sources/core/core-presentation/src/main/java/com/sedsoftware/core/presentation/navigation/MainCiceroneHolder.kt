package com.sedsoftware.core.presentation.navigation

import com.sedsoftware.core.domain.AppFlow
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import java.util.EnumMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainCiceroneHolder @Inject constructor() {
    private val containers: EnumMap<AppFlow, Cicerone<Router>> =
        EnumMap<AppFlow, Cicerone<Router>>(AppFlow::class.java)

    fun getCicerone(key: AppFlow): Cicerone<Router> {
        if (!containers.containsKey(key)) {
            containers[key] = Cicerone.create()
        }
        return containers[key] ?: Cicerone.create()
    }
}
