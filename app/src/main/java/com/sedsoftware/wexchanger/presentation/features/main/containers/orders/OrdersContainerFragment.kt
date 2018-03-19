package com.sedsoftware.wexchanger.presentation.features.main.containers.orders

import androidx.os.bundleOf
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.Layout
import com.sedsoftware.wexchanger.presentation.base.BaseContainerFragment

@Layout(R.layout.fragment_tab_container)
class OrdersContainerFragment : BaseContainerFragment(), OrdersContainerView {

  companion object {
    fun newInstance(tag: String?) = OrdersContainerFragment().apply {
      arguments = bundleOf(CONTAINER_TAG_KEY to tag)
    }
  }

//  @InjectPresenter
//  lateinit var presenter: OrdersContainerPresenter
//
//  @ProvidePresenter
//  fun providePresenter(): OrdersContainerPresenter {
//    val scope = Toothpick.openScopes(AppScope.APPLICATION, AppScope.TAB_ORDERS)
//    scope.installModules(OrdersContainerModule(this))
//    Toothpick.inject(this, scope)
//    return scope.getInstance(OrdersContainerPresenter::class.java)
//  }
}
