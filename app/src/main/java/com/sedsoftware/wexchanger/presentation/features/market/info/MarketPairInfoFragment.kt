package com.sedsoftware.wexchanger.presentation.features.market.info

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.LayoutResource
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.presentation.base.BaseFragment
import toothpick.Toothpick

@LayoutResource(R.layout.fragment_market_pair_info)
class MarketPairInfoFragment : BaseFragment(), MarketPairInfoView {

  companion object {
    fun newInstance() = MarketPairInfoFragment()
  }

  @InjectPresenter
  lateinit var presenter: MarketPairInfoPresenter

  @ProvidePresenter
  fun providePresenter(): MarketPairInfoPresenter =
    Toothpick
      .openScopes(AppScope.APPLICATION, AppScope.TAB_MARKET)
      .getInstance(MarketPairInfoPresenter::class.java)
}
