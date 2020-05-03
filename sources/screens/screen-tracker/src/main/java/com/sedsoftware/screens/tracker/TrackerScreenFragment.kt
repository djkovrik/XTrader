package com.sedsoftware.screens.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.screens.tracker.databinding.FragmentTrackerScreenBinding

class TrackerScreenFragment : BaseFragment() {

    companion object {
        fun newInstance(): TrackerScreenFragment = TrackerScreenFragment()
    }

    private val binding: FragmentTrackerScreenBinding get() = _binding!!
    private var _binding: FragmentTrackerScreenBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTrackerScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
