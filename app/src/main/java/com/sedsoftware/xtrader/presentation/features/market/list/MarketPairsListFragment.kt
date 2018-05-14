package com.sedsoftware.xtrader.presentation.features.market.list

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.sedsoftware.xtrader.R
import com.sedsoftware.xtrader.commons.annotation.Layout
import com.sedsoftware.xtrader.commons.listener.BackButtonListener
import com.sedsoftware.xtrader.di.AppScope
import com.sedsoftware.xtrader.presentation.base.BaseNestedFragment
import kotlinx.android.synthetic.main.fragment_market_pairs.*
import toothpick.Toothpick

@Layout(R.layout.fragment_market_pairs)
class MarketPairsListFragment : BaseNestedFragment(), MarketPairsListView, BackButtonListener {

  companion object {
    fun newInstance() = MarketPairsListFragment()
  }

  @InjectPresenter
  lateinit var presenter: MarketPairsListPresenter

  @ProvidePresenter
  fun providePresenter(): MarketPairsListPresenter =
    Toothpick
      .openScope(AppScope.TAB_MARKET)
      .getInstance(MarketPairsListPresenter::class.java)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    nav_button.setOnClickListener { presenter.onPairInfoClicked() }
  }

  override fun onBackPressed(): Boolean {
    presenter.onBackPressed()
    return true
  }
}
