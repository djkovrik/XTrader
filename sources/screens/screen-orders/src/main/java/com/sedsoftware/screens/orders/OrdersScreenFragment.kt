package com.sedsoftware.screens.orders

import com.sedsoftware.core.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersScreenFragment : BaseFragment(R.layout.fragment_orders_screen) {

    companion object {
        fun newInstance(): OrdersScreenFragment = OrdersScreenFragment()
    }
}
