package com.sedsoftware.screens.tracker

import com.sedsoftware.core.presentation.base.BaseRegularFragment

class TrackerScreenFragment : BaseRegularFragment() {

    companion object {
        fun newInstance(): TrackerScreenFragment = TrackerScreenFragment()
    }

    override val layoutResId: Int = R.layout.fragment_tracker_screen
}
