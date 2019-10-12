package com.sedsoftware.screens.tools

import com.sedsoftware.core.presentation.base.BaseFragment

class ToolsScreenFragment : BaseFragment() {

    companion object {
        fun newInstance(): ToolsScreenFragment = ToolsScreenFragment()
    }

    override val layoutResId: Int = R.layout.fragment_tools_screen
}
