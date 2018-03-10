package com.sedsoftware.wexchanger.presentation.features.main.containers.market

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.LayoutResource
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.presentation.base.BaseContainerFragment
import com.sedsoftware.wexchanger.presentation.features.main.containers.di.MarketContainerModule
import ru.terrakok.cicerone.Navigator
import toothpick.Toothpick
import javax.inject.Inject

@LayoutResource(R.layout.fragment_tab_container)
class MarketContainerFragment : BaseContainerFragment(), MarketContainerView {

  companion object {
    fun newInstance(tag: String?) = MarketContainerFragment().apply {
      arguments = Bundle().apply {
        putString(CONTAINER_TAG_KEY, tag)
      }
    }
  }

  @Inject
  lateinit var localNavigator: Navigator

  @InjectPresenter
  lateinit var presenter: MarketContainerPresenter

  @ProvidePresenter
  fun providePresenter(): MarketContainerPresenter =
    Toothpick
      .openScopes(AppScope.APPLICATION, AppScope.TAB_MARKET)
      .also { scope -> scope.installModules(MarketContainerModule(this)) }
      .also { scope -> Toothpick.inject(this, scope) }
      .getInstance(MarketContainerPresenter::class.java)

  override fun onResume() {
    super.onResume()
    cicerone.navigatorHolder.setNavigator(localNavigator)
  }

  override fun onPause() {
    cicerone.navigatorHolder.removeNavigator()
    super.onPause()
  }
}
