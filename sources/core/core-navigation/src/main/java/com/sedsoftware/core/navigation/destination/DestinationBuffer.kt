package com.sedsoftware.core.navigation.destination

import androidx.navigation.NavController
import com.sedsoftware.core.navigation.NavControllerHolder
import java.util.LinkedList
import java.util.Queue
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DestinationBuffer @Inject constructor() : NavControllerHolder {
    private var controller: NavController? = null
    private var pendingDestinations: Queue<Destination> = LinkedList()

    override fun setNavController(newController: NavController) {
        this.controller = newController
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
            destination.getNavOptions(),
            destination.getNavExtras()
        ) ?: pendingDestinations.add(destination)
    }
}
