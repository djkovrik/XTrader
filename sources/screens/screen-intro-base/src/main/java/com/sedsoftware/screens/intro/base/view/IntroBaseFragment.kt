package com.sedsoftware.screens.intro.base.view

import android.os.Bundle
import android.view.View
import com.arkivanov.mvikotlin.androidxlifecycleinterop.asMviLifecycle
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.viewbinding.viewBinding
import com.sedsoftware.screens.intro.base.R
import com.sedsoftware.screens.intro.base.controller.IntroBaseController
import com.sedsoftware.screens.intro.base.databinding.FragmentIntroBaseBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IntroBaseFragment : BaseFragment(R.layout.fragment_intro_base) {

    companion object {
        fun newInstance(): IntroBaseFragment = IntroBaseFragment()
    }

    private val binding: FragmentIntroBaseBinding by viewBinding()

    @Inject
    lateinit var controller: IntroBaseController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controller.onViewCreated(
            view = IntroBaseViewImpl(binding),
            lifecycle = viewLifecycleOwner.lifecycle.asMviLifecycle(),
            errorHandlerView = this
        )
    }
}
