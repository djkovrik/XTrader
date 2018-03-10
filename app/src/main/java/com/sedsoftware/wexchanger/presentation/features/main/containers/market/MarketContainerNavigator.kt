package com.sedsoftware.wexchanger.presentation.features.main.containers.market

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.presentation.features.market.info.MarketPairInfoFragment
import com.sedsoftware.wexchanger.presentation.features.market.list.MarketPairsListFragment
import com.sedsoftware.wexchanger.presentation.navigation.AppScreen
import ru.terrakok.cicerone.android.SupportAppNavigator
import javax.inject.Inject

class MarketContainerNavigator @Inject constructor(
  fragment: MarketContainerFragment
) : SupportAppNavigator(fragment.activity, fragment.childFragmentManager, R.id.tab_container) {

  override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? = null

  override fun createFragment(screenKey: String?, data: Any?): Fragment? = when (screenKey) {
    AppScreen.MARKET_PAIRS_LIST -> MarketPairsListFragment.newInstance()
    AppScreen.MARKET_PAIR_INFO -> MarketPairInfoFragment.newInstance()
    else -> null
  }
}
