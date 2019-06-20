package com.sedsoftware.screens.main.navigation.destination

import androidx.navigation.NavController
import com.sedsoftware.screens.main.navigation.NavControllerHolder
import java.util.LinkedList
import java.util.Queue
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DestinationsBuffer @Inject constructor() : NavControllerHolder {
    private var navController: NavController? = null
    private var pendingDestinations: Queue<Destination> = LinkedList()

    override fun setNavController(controller: NavController) {
        navController = controller
        while (pendingDestinations.isNotEmpty()) {
            navController?.let { navigateTo(pendingDestinations.poll()) } ?: break
        }
    }

    override fun removeNavController() {
        navController = null
    }

    fun navigateTo(destination: Destination) {
        navController?.navigate(
            destination.routeId,
            destination.routeArguments,
            destination.routeOptions,
            destination.routeExtras
        ) ?: pendingDestinations.add(destination)
    }
}
