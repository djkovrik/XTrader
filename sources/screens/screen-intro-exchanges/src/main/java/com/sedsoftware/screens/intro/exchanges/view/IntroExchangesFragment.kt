package com.sedsoftware.screens.intro.exchanges.view

import android.os.Bundle
import android.view.View
import com.arkivanov.mvikotlin.androidxlifecycleinterop.asMviLifecycle
import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.core.domain.tools.ResourceManager
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.viewbinding.viewBinding
import com.sedsoftware.screens.intro.exchanges.R
import com.sedsoftware.screens.intro.exchanges.controller.IntroExchangesController
import com.sedsoftware.screens.intro.exchanges.databinding.FragmentIntroExchangesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IntroExchangesFragment : BaseFragment(R.layout.fragment_intro_exchanges) {

    companion object {
        fun newInstance(): IntroExchangesFragment = IntroExchangesFragment()
    }

    private val binding: FragmentIntroExchangesBinding by viewBinding()

    @Inject
    lateinit var assetsProvider: AssetsProvider

    @Inject
    lateinit var resourceManager: ResourceManager

    @Inject
    lateinit var controller: IntroExchangesController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controller.onViewCreated(
            view = IntroExchangesViewImpl(binding, assetsProvider, resourceManager),
            lifecycle = viewLifecycleOwner.lifecycle.asMviLifecycle(),
            errorHandlerView = this
        )
    }
}
