package com.sedsoftware.core.navigation.destination

import androidx.navigation.NavController
import com.sedsoftware.core.navigation.NavControllerHolder
import java.util.LinkedList
import java.util.Queue

class DestinationsBuffer : NavControllerHolder {
    private var controller: NavController? = null
    private var pendingDestinations: Queue<Destination> = LinkedList()

    override fun setNavController(controller: NavController) {
        this.controller = controller
        while (pendingDestinations.isNotEmpty()) {
            this.controller?.let { goToDestination(pendingDestinations.poll()) } ?: break
        }
    }

    override fun removeNavController() {
        this.controller = null
    }

    fun goToDestination(destination: Destination) {
        controller?.navigate(
            destination.actionId,
            destination.arguments,
            destination.navOptions,
            destination.navExtras
        ) ?: pendingDestinations.add(destination)
    }
}
