package com.sedsoftware.wexchanger.presentation.features.main.containers.tracker

import androidx.os.bundleOf
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.Layout
import com.sedsoftware.wexchanger.presentation.base.BaseContainerFragment
import ru.terrakok.cicerone.Navigator

@Layout(R.layout.fragment_tab_container)
class TrackerContainerFragment : BaseContainerFragment() {

  companion object {
    fun newInstance(tag: String?) = TrackerContainerFragment().apply {
      arguments = bundleOf(CONTAINER_TAG_KEY to tag)
    }
  }

  override val localNavigator: Navigator
    get() = Navigator { }
}
