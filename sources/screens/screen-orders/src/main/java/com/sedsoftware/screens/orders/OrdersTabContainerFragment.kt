package com.sedsoftware.screens.orders

import com.sedsoftware.core.presentation.base.BaseFragment

class OrdersTabContainerFragment : BaseFragment() {

    companion object {
        fun newInstance(): OrdersTabContainerFragment = OrdersTabContainerFragment()
    }

    override val layoutResId: Int = R.layout.layout_container
}
