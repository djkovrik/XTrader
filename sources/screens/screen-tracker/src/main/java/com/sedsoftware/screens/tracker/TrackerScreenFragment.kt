package com.sedsoftware.screens.tracker

import com.sedsoftware.core.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackerScreenFragment : BaseFragment(R.layout.fragment_tracker_screen) {

    companion object {
        fun newInstance(): TrackerScreenFragment = TrackerScreenFragment()
    }
}
