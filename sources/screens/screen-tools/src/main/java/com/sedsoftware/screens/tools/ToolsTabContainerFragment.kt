package com.sedsoftware.screens.tools

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.sedsoftware.core.presentation.base.BaseFragment

class ToolsTabContainerFragment : BaseFragment() {

    companion object {
        fun newInstance(): ToolsTabContainerFragment = ToolsTabContainerFragment()
    }

    override val layoutResId: Int = R.layout.layout_container

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.material_color_green_400))
    }
}
