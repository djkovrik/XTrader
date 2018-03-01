package com.sedsoftware.wexchanger.presentation.base

import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.LayoutResource

@LayoutResource(R.layout.fragment_tab_container)
abstract class BaseTabFragment : BaseFragment() {

  protected companion object {
    const val CONTAINER_TAG_KEY = "CONTAINER_TAG_KEY"
  }
}
