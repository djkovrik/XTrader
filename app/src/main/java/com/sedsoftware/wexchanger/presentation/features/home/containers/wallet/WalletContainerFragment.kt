package com.sedsoftware.wexchanger.presentation.features.home.containers.wallet

import android.os.Bundle
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.LayoutResource
import com.sedsoftware.wexchanger.presentation.base.BaseTabFragment

@LayoutResource(R.layout.fragment_tab_container)
class WalletContainerFragment : BaseTabFragment() {

  companion object {
    fun newInstance(tag: String?) = WalletContainerFragment().apply {
      arguments = Bundle().apply {
        putString(CONTAINER_TAG_KEY, tag)
      }
    }
  }
}
