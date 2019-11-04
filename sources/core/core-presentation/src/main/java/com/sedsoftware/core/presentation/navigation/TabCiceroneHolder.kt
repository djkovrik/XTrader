package com.sedsoftware.core.presentation.navigation

import com.sedsoftware.core.di.scope.FlowScope
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@FlowScope
class TabCiceroneHolder @Inject constructor() {

    private val containers: HashMap<String, Cicerone<Router>> = HashMap()

    fun getCicerone(containerTag: String): Cicerone<Router> {
        if (!containers.containsKey(containerTag)) {
            containers[containerTag] = Cicerone.create()
        }
        return containers[containerTag] ?: Cicerone.create()
    }
}
