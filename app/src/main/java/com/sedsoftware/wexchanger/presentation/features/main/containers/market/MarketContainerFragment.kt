package com.sedsoftware.wexchanger.presentation.features.main.containers.market

import androidx.os.bundleOf
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.Layout
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.presentation.base.BaseContainerFragment
import com.sedsoftware.wexchanger.presentation.features.main.di.MarketContainerModule
import toothpick.Toothpick

@Layout(R.layout.fragment_tab_container)
class MarketContainerFragment : BaseContainerFragment(), MarketContainerView {

  companion object {
    fun newInstance(tag: String?) = MarketContainerFragment().apply {
      arguments = bundleOf(CONTAINER_TAG_KEY to tag)
    }
  }

  @InjectPresenter
  lateinit var presenter: MarketContainerPresenter

  @ProvidePresenter
  fun providePresenter(): MarketContainerPresenter {
    val scope = Toothpick.openScopes(AppScope.APPLICATION, AppScope.TAB_MARKET)
    scope.installModules(MarketContainerModule(this))
    Toothpick.inject(this, scope)
    return scope.getInstance(MarketContainerPresenter::class.java)
  }
}
