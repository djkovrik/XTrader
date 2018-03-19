package com.sedsoftware.wexchanger.presentation.features.main.containers.wallet

import androidx.os.bundleOf
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.Layout
import com.sedsoftware.wexchanger.presentation.base.BaseContainerFragment
import ru.terrakok.cicerone.Navigator

@Layout(R.layout.fragment_tab_container)
class WalletContainerFragment : BaseContainerFragment() {

  companion object {
    fun newInstance(tag: String?) = WalletContainerFragment().apply {
      arguments = bundleOf(CONTAINER_TAG_KEY to tag)
    }
  }
}
