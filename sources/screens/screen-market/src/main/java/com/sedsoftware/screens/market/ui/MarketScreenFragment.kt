package com.sedsoftware.screens.market.ui

import android.os.Bundle
import android.view.Display
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.arkivanov.mvikotlin.androidxlifecycleinterop.asMviLifecycle
import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.management.DaggerComponentManager
import com.sedsoftware.core.di.management.HasDaggerComponent
import com.sedsoftware.core.di.management.HasInject
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.viewbinding.viewBinding
import com.sedsoftware.screens.market.R.layout
import com.sedsoftware.screens.market.controller.MarketController
import com.sedsoftware.screens.market.databinding.FragmentMarketScreenBinding
import com.sedsoftware.screens.market.di.MarketScreenComponent
import com.sedsoftware.screens.market.view.MarketListViewImpl
import com.sedsoftware.screens.market.view.PairSelectionViewImpl
import javax.inject.Inject

class MarketScreenFragment :
    BaseFragment(layout.fragment_market_screen), HasDaggerComponent<MarketScreenComponent>, HasInject {

    companion object {
        fun newInstance(): MarketScreenFragment =
            MarketScreenFragment()
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
            selectorView = PairSelectionViewImpl(requireContext(), onBackPressedCallback, defaultDisplay, binding),
            marketView = MarketListViewImpl(binding),
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

    override fun getComponent(): MarketScreenComponent {
        val activityTools = DaggerComponentManager.get<ActivityToolsProvider>()
        return MarketScreenComponent.Initializer.init(activityTools)
    }

    override fun inject() {
        DaggerComponentManager
            .get<MarketScreenComponent>()
            .inject(this)
    }
}
