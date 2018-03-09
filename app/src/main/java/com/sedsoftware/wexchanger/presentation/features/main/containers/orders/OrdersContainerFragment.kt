package com.sedsoftware.wexchanger.presentation.features.main.containers.orders

import android.os.Bundle
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.LayoutResource
import com.sedsoftware.wexchanger.presentation.base.BaseTabFragment

@LayoutResource(R.layout.fragment_tab_container)
class OrdersContainerFragment : BaseTabFragment() {

  companion object {
    fun newInstance(tag: String?) = OrdersContainerFragment().apply {
      arguments = Bundle().apply {
        putString(CONTAINER_TAG_KEY, tag)
      }
    }
  }
}
