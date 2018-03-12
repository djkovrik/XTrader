package com.sedsoftware.wexchanger.presentation.features.main.containers.wallet

import android.os.Bundle
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.Layout
import com.sedsoftware.wexchanger.presentation.base.BaseContainerFragment
import ru.terrakok.cicerone.Navigator

@Layout(R.layout.fragment_tab_container)
class WalletContainerFragment : BaseContainerFragment() {

  companion object {
    fun newInstance(tag: String?) = WalletContainerFragment().apply {
      arguments = Bundle().apply {
        putString(CONTAINER_TAG_KEY, tag)
      }
    }
  }

  override val localNavigator: Navigator
    get() = Navigator { }
}
