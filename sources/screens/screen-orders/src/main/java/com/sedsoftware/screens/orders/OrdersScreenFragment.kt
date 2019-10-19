package com.sedsoftware.screens.orders

import com.sedsoftware.core.presentation.base.BaseRegularFragment

class OrdersScreenFragment : BaseRegularFragment() {

    companion object {
        fun newInstance(): OrdersScreenFragment = OrdersScreenFragment()
    }

    override val layoutResId: Int = R.layout.fragment_orders_screen
}
