package com.sedsoftware.screens.tracker

import com.sedsoftware.core.presentation.base.BaseFragment

class TrackerTabContainerFragment : BaseFragment() {

    companion object {
        fun newInstance(): TrackerTabContainerFragment = TrackerTabContainerFragment()
    }

    override val layoutResId: Int = R.layout.layout_container
}
