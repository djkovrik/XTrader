package com.sedsoftware.core.presentation.extension

import androidx.fragment.app.Fragment
import com.sedsoftware.core.navigation.destination.DestinationFactory
import com.sedsoftware.core.presentation.base.BaseViewModel

fun BaseViewModel.getDestinations(fragmentClass: Class<out Fragment>): DestinationFactory {

    val factory = factories[fragmentClass]
            ?: factories.asIterable().firstOrNull { fragmentClass.isAssignableFrom(it.key) }?.value
            ?: throw IllegalArgumentException("Factory for $fragmentClass not found")

    return try {
        factory.get() as DestinationFactory
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}
