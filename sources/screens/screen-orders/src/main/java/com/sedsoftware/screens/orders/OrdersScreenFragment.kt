package com.sedsoftware.screens.orders

import com.sedsoftware.core.presentation.base.BaseFragment

class OrdersScreenFragment : BaseFragment() {

    companion object {

        fun newInstance(): OrdersScreenFragment = OrdersScreenFragment()
    }

    override fun getLayoutResId(): Int =
        R.layout.fragment_orders_screen
}
