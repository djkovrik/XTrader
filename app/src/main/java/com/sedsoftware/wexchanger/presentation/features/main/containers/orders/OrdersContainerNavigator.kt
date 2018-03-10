package com.sedsoftware.wexchanger.presentation.features.main.containers.orders

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.presentation.features.orders.helper.OrdersHelperFragment
import com.sedsoftware.wexchanger.presentation.features.orders.list.OrdersListFragment
import com.sedsoftware.wexchanger.presentation.navigation.AppScreen
import ru.terrakok.cicerone.android.SupportAppNavigator
import javax.inject.Inject

class OrdersContainerNavigator @Inject constructor(
  fragment: OrdersContainerFragment
) : SupportAppNavigator(fragment.activity, fragment.childFragmentManager, R.id.tab_container) {

  override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? = null

  override fun createFragment(screenKey: String?, data: Any?): Fragment? = when (screenKey) {
    AppScreen.ORDERS_LIST -> OrdersListFragment.newInstance()
    AppScreen.ORDERS_HELPER -> OrdersHelperFragment.newInstance()
    else -> null
  }
}
