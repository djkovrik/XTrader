package com.sedsoftware.screens.intro.exchanges.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arkivanov.mvikotlin.androidxlifecycleinterop.asMviLifecycle
import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.management.DaggerComponentManager
import com.sedsoftware.core.di.management.HasDaggerComponent
import com.sedsoftware.core.di.management.HasInject
import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.core.domain.tools.ResourceManager
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.screens.intro.exchanges.controller.IntroExchangesController
import com.sedsoftware.screens.intro.exchanges.databinding.FragmentIntroExchangesBinding
import com.sedsoftware.screens.intro.exchanges.di.IntroExchangesComponent
import javax.inject.Inject

class IntroExchangesFragment : BaseFragment(), HasDaggerComponent<IntroExchangesComponent>, HasInject {

    companion object {
        fun newInstance(): IntroExchangesFragment =
            IntroExchangesFragment()
    }

    private val binding: FragmentIntroExchangesBinding get() = _binding!!
    private var _binding: FragmentIntroExchangesBinding? = null

    @Inject
    lateinit var assetsProvider: AssetsProvider

    @Inject
    lateinit var resourceManager: ResourceManager

    @Inject
    lateinit var controller: IntroExchangesController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentIntroExchangesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controller.onViewCreated(
            view = IntroExchangesViewImpl(binding, assetsProvider, resourceManager),
            lifecycle = viewLifecycleOwner.lifecycle.asMviLifecycle(),
            errorHandlerView = this
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getComponent(): IntroExchangesComponent {
        val activityTools = DaggerComponentManager.get<ActivityToolsProvider>()
        return IntroExchangesComponent.Initializer.init(activityTools)
    }

    override fun getComponentKey(): String =
        this::class.java.simpleName

    override fun inject() {
        DaggerComponentManager
            .get<IntroExchangesComponent>()
            .inject(this)
    }
}
