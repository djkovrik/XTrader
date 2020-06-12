package com.sedsoftware.screens.market.view

import android.os.Bundle
import android.view.Display
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.arkivanov.mvikotlin.androidxlifecycleinterop.asMviLifecycle
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.viewbinding.viewBinding
import com.sedsoftware.screens.market.R.layout
import com.sedsoftware.screens.market.controller.MarketController
import com.sedsoftware.screens.market.databinding.FragmentMarketScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MarketScreenFragment : BaseFragment(layout.fragment_market_screen) {

    companion object {
        fun newInstance(): MarketScreenFragment = MarketScreenFragment()
    }

    private val binding: FragmentMarketScreenBinding by viewBinding()

    private val onBackPressedCallback = object : OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
            controller.onBackPressed()
        }
    }

    @Inject
    lateinit var defaultDisplay: Display

    @Inject
    lateinit var controller: MarketController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controller.onViewCreated(
            view = MarketViewImpl(requireContext(), onBackPressedCallback, defaultDisplay, binding),
            lifecycle = viewLifecycleOwner.lifecycle.asMviLifecycle(),
            errorHandlerView = this
        )
    }

    override fun onStart() {
        super.onStart()
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
    }

    override fun onStop() {
        super.onStop()
        onBackPressedCallback.remove()
    }
}
