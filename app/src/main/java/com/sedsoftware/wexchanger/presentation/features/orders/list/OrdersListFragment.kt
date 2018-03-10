package com.sedsoftware.wexchanger.presentation.features.orders.list

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.LayoutResource
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_orders_list.*
import toothpick.Toothpick

@LayoutResource(R.layout.fragment_orders_list)
class OrdersListFragment : BaseFragment(), OrdersListView {

  companion object {
    fun newInstance() = OrdersListFragment()
  }

  @InjectPresenter
  lateinit var presenter: OrdersListPresenter

  @ProvidePresenter
  fun providePresenter(): OrdersListPresenter =
    Toothpick
      .openScopes(AppScope.APPLICATION, AppScope.TAB_ORDERS)
      .getInstance(OrdersListPresenter::class.java)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    helper_button.setOnClickListener { presenter.onOrderClicked() }
  }
}
