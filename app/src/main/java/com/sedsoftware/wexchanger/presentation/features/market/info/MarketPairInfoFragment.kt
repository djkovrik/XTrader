package com.sedsoftware.wexchanger.presentation.features.market.info

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.Layout
import com.sedsoftware.wexchanger.commons.listener.BackButtonListener
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.presentation.base.BaseNestedFragment
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
