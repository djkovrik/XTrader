package com.sedsoftware.wexchanger.presentation.features.orders.list

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.Layout
import com.sedsoftware.wexchanger.commons.listener.BackButtonListener
import com.sedsoftware.wexchanger.di.AppScope
import com.sedsoftware.wexchanger.presentation.base.BaseNestedFragment
import kotlinx.android.synthetic.main.fragment_orders_list.*
import toothpick.Toothpick

@Layout(R.layout.fragment_orders_list)
class OrdersListFragment : BaseNestedFragment(), OrdersListView, BackButtonListener {

  companion object {
    fun newInstance() = OrdersListFragment()
  }

  @InjectPresenter
  lateinit var presenter: OrdersListPresenter

  @ProvidePresenter
  fun providePresenter(): OrdersListPresenter =
    Toothpick
      .openScope(AppScope.TAB_ORDERS)
      .getInstance(OrdersListPresenter::class.java)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    helper_button.setOnClickListener { presenter.onOrderClicked() }
  }

  override fun onBackPressed(): Boolean {
    presenter.onBackPressed()
    return true
  }
}
