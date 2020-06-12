package com.sedsoftware.screens.tools

import com.sedsoftware.core.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToolsScreenFragment : BaseFragment(R.layout.fragment_tools_screen) {

    companion object {
        fun newInstance(): ToolsScreenFragment = ToolsScreenFragment()
    }
}
