package com.sedsoftware.wexchanger.presentation.features.main.containers.market

import android.os.Bundle
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.LayoutResource
import com.sedsoftware.wexchanger.presentation.base.BaseTabFragment

@LayoutResource(R.layout.fragment_tab_container)
class MarketContainerFragment : BaseTabFragment() {

  companion object {
    fun newInstance(tag: String?) = MarketContainerFragment().apply {
      arguments = Bundle().apply {
        putString(CONTAINER_TAG_KEY, tag)
      }
    }
  }
}
