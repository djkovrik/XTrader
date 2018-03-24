package com.sedsoftware.wexchanger.presentation.features.market.list

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.Layout
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.presentation.base.BaseNestedFragment
import kotlinx.android.synthetic.main.fragment_market_pairs.*
import toothpick.Toothpick

@Layout(R.layout.fragment_market_pairs)
class MarketPairsListFragment : BaseNestedFragment(), MarketPairsListView {

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
}
