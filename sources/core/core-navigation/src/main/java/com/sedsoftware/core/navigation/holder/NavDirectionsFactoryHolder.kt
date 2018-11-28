package com.sedsoftware.core.navigation.holder

import com.sedsoftware.core.navigation.factory.NavDirectionsFactory

interface NavDirectionsFactoryHolder {
    fun getNavDirectionsFactory(): NavDirectionsFactory
}
