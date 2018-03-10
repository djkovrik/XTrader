package com.sedsoftware.wexchanger.presentation.features.main.containers.wallet

import android.os.Bundle
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.LayoutResource
import com.sedsoftware.wexchanger.presentation.base.BaseContainerFragment

@LayoutResource(R.layout.fragment_tab_container)
class WalletContainerFragment : BaseContainerFragment() {

  companion object {
    fun newInstance(tag: String?) = WalletContainerFragment().apply {
      arguments = Bundle().apply {
        putString(CONTAINER_TAG_KEY, tag)
      }
    }
  }
}
