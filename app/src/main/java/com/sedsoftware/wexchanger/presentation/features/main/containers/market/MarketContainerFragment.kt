package com.sedsoftware.wexchanger.presentation.features.main.containers.market

import android.content.Context
import androidx.os.bundleOf
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.Layout
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.presentation.base.BaseContainerFragment
import com.sedsoftware.wexchanger.presentation.features.main.di.MarketContainerModule
import ru.terrakok.cicerone.Navigator
import toothpick.Toothpick

@Layout(R.layout.fragment_tab_container)
class MarketContainerFragment : BaseContainerFragment(), MarketContainerView {

  companion object {
    fun newInstance(tag: String?) = MarketContainerFragment().apply {
      arguments = bundleOf(CONTAINER_TAG_KEY to tag)
    }
  }

  override val localNavigator: Navigator
    get() =
      Toothpick
        .openScope(AppScope.TAB_MARKET)
        .getInstance(Navigator::class.java)

  @InjectPresenter
  lateinit var presenter: MarketContainerPresenter

  @ProvidePresenter
  fun providePresenter(): MarketContainerPresenter =
    Toothpick
      .openScope(AppScope.TAB_MARKET)
      .getInstance(MarketContainerPresenter::class.java)

  override fun onAttach(context: Context?) {
    Toothpick
      .openScopes(AppScope.APPLICATION, AppScope.TAB_MARKET)
      .apply {
        installModules(MarketContainerModule(this@MarketContainerFragment))
        Toothpick.inject(this@MarketContainerFragment, this)
      }

    super.onAttach(context)
  }

  override fun onDetach() {
    Toothpick.closeScope(AppScope.TAB_MARKET)
    super.onDetach()
  }
}
