package com.sedsoftware.screens.main.navigation

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {

    private val router: Router =
        Router()

    fun getRouter(): Router =
        router

    fun getNavControllerHolder(): NavControllerHolder =
        router.getNavControllerHolder()
}
