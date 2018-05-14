package com.sedsoftware.xtrader.presentation.features.market.info

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.sedsoftware.xtrader.R
import com.sedsoftware.xtrader.commons.annotation.Layout
import com.sedsoftware.xtrader.commons.listener.BackButtonListener
import com.sedsoftware.xtrader.di.AppScope
import com.sedsoftware.xtrader.presentation.base.BaseNestedFragment
import toothpick.Toothpick

@Layout(R.layout.fragment_market_pair_info)
class MarketPairInfoFragment : BaseNestedFragment(), MarketPairInfoView, BackButtonListener {

  companion object {
    fun newInstance() = MarketPairInfoFragment()
  }

  @InjectPresenter
  lateinit var presenter: MarketPairInfoPresenter

  @ProvidePresenter
  fun providePresenter(): MarketPairInfoPresenter =
    Toothpick
      .openScope(AppScope.TAB_MARKET)
      .getInstance(MarketPairInfoPresenter::class.java)

  override fun onBackPressed(): Boolean {
    presenter.onBackPressed()
    return true
  }
}
