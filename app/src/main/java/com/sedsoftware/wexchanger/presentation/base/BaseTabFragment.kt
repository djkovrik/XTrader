package com.sedsoftware.wexchanger.presentation.base

import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.LayoutResource
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.presentation.navigation.LocalNavigatorHolder
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import toothpick.Toothpick

@LayoutResource(R.layout.fragment_tab_container)
abstract class BaseTabFragment : BaseFragment() {

  protected companion object {
    const val CONTAINER_TAG_KEY = "CONTAINER_TAG_KEY"
  }

  val router: Router
    get() = cicerone.router

  protected val cicerone: Cicerone<Router>
    get() = navigatorHolder.getCicerone(containerTag)

  private val containerTag: String
    get() = arguments?.getString(CONTAINER_TAG_KEY)
        ?: throw IllegalArgumentException("Container tag must be provided via arguments")

  private var navigatorHolder: LocalNavigatorHolder =
    Toothpick
      .openScope(AppScope.APPLICATION)
      .getInstance(LocalNavigatorHolder::class.java)
}
