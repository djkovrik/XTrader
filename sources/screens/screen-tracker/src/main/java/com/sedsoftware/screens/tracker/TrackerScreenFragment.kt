package com.sedsoftware.screens.tracker

import com.sedsoftware.core.presentation.base.BaseFragment

class TrackerScreenFragment : BaseFragment() {

    companion object {
        fun newInstance(): TrackerScreenFragment = TrackerScreenFragment()
    }

    override val layoutResId: Int = R.layout.fragment_tracker_screen
}
