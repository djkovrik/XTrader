package com.sedsoftware.screens.tickers.ui

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
import com.sedsoftware.screens.tickers.R.layout
import com.sedsoftware.screens.tickers.controller.TickersController
import com.sedsoftware.screens.tickers.databinding.FragmentTickersScreenBinding
import com.sedsoftware.screens.tickers.di.TickersScreenComponent
import com.sedsoftware.screens.tickers.view.TickersListViewImpl
import com.sedsoftware.screens.tickers.view.PairSelectionViewImpl
import javax.inject.Inject

class TickersScreenFragment :
    BaseFragment(layout.fragment_tickers_screen), HasDaggerComponent<TickersScreenComponent>, HasInject {

    companion object {
        fun newInstance(): TickersScreenFragment = TickersScreenFragment()
    }

    private val binding: FragmentTickersScreenBinding by viewBinding()

    private val onBackPressedCallback = object : OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
            invoker.runAction()
        }
    }

    @Inject
    lateinit var display: Display

    @Inject
    lateinit var invoker: OnBackPressedInvoker

    @Inject
    lateinit var controller: TickersController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controller.onViewCreated(
            selectorView = PairSelectionViewImpl(requireContext(), onBackPressedCallback, display, invoker, binding),
            tickersView = TickersListViewImpl(binding),
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

    override fun getComponent(): TickersScreenComponent {
        val activityTools = DaggerComponentManager.get<ActivityToolsProvider>()
        return TickersScreenComponent.Initializer.init(activityTools)
    }

    override fun inject() {
        DaggerComponentManager
            .get<TickersScreenComponent>()
            .inject(this)
    }
}
