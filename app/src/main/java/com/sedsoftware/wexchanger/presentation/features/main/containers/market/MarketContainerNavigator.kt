package com.sedsoftware.wexchanger.presentation.features.main.containers.market

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.presentation.features.market.info.MarketPairInfoFragment
import com.sedsoftware.wexchanger.presentation.features.market.list.MarketPairsListFragment
import com.sedsoftware.wexchanger.presentation.navigation.AppScreen
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import javax.inject.Inject

class MarketContainerNavigator @Inject constructor(
  fragment: MarketContainerFragment
) : SupportAppNavigator(fragment.activity, fragment.childFragmentManager, R.id.tab_container) {

  override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? =
    null

  override fun createFragment(screenKey: String?, data: Any?): Fragment? = when (screenKey) {
    AppScreen.MARKET_PAIRS_LIST -> MarketPairsListFragment.newInstance()
    AppScreen.MARKET_PAIR_INFO -> MarketPairInfoFragment.newInstance()
    else -> null
  }

  @Suppress("ComplexCondition")
  override fun setupFragmentTransactionAnimation(
    command: Command?,
    currentFragment: Fragment?,
    nextFragment: Fragment?,
    fragmentTransaction: FragmentTransaction?
  ) {

    if (command is Forward &&
      nextFragment is MarketPairInfoFragment &&
      currentFragment is MarketPairsListFragment
    ) {
      fragmentTransaction?.setCustomAnimations(
        R.anim.enter_from_right,
        R.anim.exit_from_right,
        R.anim.enter_to_right,
        R.anim.exit_to_right
      )
    }
  }
}
