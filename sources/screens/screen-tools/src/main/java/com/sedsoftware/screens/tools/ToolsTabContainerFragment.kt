package com.sedsoftware.screens.tools

import com.sedsoftware.core.presentation.base.BaseFragment

class ToolsTabContainerFragment : BaseFragment() {

    companion object {
        fun newInstance(): ToolsTabContainerFragment = ToolsTabContainerFragment()
    }

    override val layoutResId: Int = R.layout.layout_container
}
