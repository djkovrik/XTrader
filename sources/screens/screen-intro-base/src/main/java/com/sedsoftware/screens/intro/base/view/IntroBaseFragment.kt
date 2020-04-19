package com.sedsoftware.screens.intro.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arkivanov.mvikotlin.androidxlifecycleinterop.asMviLifecycle
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.screens.intro.base.controller.IntroBaseController
import com.sedsoftware.screens.intro.base.databinding.FragmentIntroBaseBinding

class IntroBaseFragment : BaseFragment() {

    companion object {
        fun newInstance(): IntroBaseFragment =
            IntroBaseFragment()
    }

    private val binding: FragmentIntroBaseBinding get() = _binding!!
    private var _binding: FragmentIntroBaseBinding? = null

    // TODO DI
    private val controller: IntroBaseController = IntroBaseController()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentIntroBaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controller.onViewCreated(
            introBaseView = IntroBaseViewImpl(binding),
            viewLifecycle = viewLifecycleOwner.lifecycle.asMviLifecycle()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
