package com.sedsoftware.core.navigation

import com.sedsoftware.core.navigation.routing.NavControllerHolder
import com.sedsoftware.core.navigation.routing.Router

class XTNavigator {

    val router: Router by lazy {
        Router()
    }

    val navControllerHolder: NavControllerHolder by lazy {
        router.destinationsBuffer
    }
}
