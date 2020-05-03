package com.sedsoftware.screens.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.screens.tools.databinding.FragmentToolsScreenBinding

class ToolsScreenFragment : BaseFragment() {

    companion object {
        fun newInstance(): ToolsScreenFragment = ToolsScreenFragment()
    }

    private val binding: FragmentToolsScreenBinding get() = _binding!!
    private var _binding: FragmentToolsScreenBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentToolsScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
