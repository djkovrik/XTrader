package com.sedsoftware.xtrader.presentation.base

import com.sedsoftware.xtrader.R
import com.sedsoftware.xtrader.commons.annotation.Layout
import com.sedsoftware.xtrader.commons.listener.BackButtonListener
import com.sedsoftware.xtrader.commons.provider.RouterProvider
import com.sedsoftware.xtrader.di.AppScope
import com.sedsoftware.xtrader.presentation.navigation.LocalNavigatorHolder
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import toothpick.Toothpick

@Layout(R.layout.fragment_tab_container)
abstract class BaseContainerFragment : BaseFragment(), BackButtonListener, RouterProvider {

  protected companion object {
    const val CONTAINER_TAG_KEY = "CONTAINER_TAG_KEY"
  }

  protected abstract val localNavigator: Navigator

  private val localNavigatorHolder: LocalNavigatorHolder =
    Toothpick
      .openScope(AppScope.APPLICATION)
      .apply { Toothpick.inject(this@BaseContainerFragment, this) }
      .getInstance(LocalNavigatorHolder::class.java)

  val router: Router
    get() = cicerone.router

  protected val cicerone: Cicerone<Router>
    get() = localNavigatorHolder.getCicerone(containerTag)

  private val containerTag: String
    get() = arguments?.getString(CONTAINER_TAG_KEY)
        ?: throw IllegalArgumentException("Container tag must be provided via arguments")

  override fun onResume() {
    super.onResume()
    cicerone.navigatorHolder.setNavigator(localNavigator)
  }

  override fun onPause() {
    cicerone.navigatorHolder.removeNavigator()
    super.onPause()
  }

  override fun onBackPressed(): Boolean {
    val fragment = childFragmentManager.findFragmentById(R.id.tab_container)

    return when {
      (fragment is BackButtonListener && fragment.onBackPressed()) -> {
        true
      }
      else -> {
        (activity as RouterProvider).getCurrentRouter().exit()
        true
      }
    }
  }

  override fun getCurrentRouter(): Router = router
}
