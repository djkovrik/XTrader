package com.sedsoftware.wexchanger.presentation.features.main.containers.tracker

import android.os.Bundle
import com.sedsoftware.wexchanger.R
import com.sedsoftware.wexchanger.commons.annotation.LayoutResource
import com.sedsoftware.wexchanger.presentation.base.BaseTabFragment

@LayoutResource(R.layout.fragment_tab_container)
class TrackerContainerFragment : BaseTabFragment() {

  companion object {
    fun newInstance(tag: String?) = TrackerContainerFragment().apply {
      arguments = Bundle().apply {
        putString(CONTAINER_TAG_KEY, tag)
      }
    }
  }
}
