package com.sedsoftware.screens.intro.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arkivanov.mvikotlin.androidxlifecycleinterop.asMviLifecycle
import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.management.DaggerComponentManager
import com.sedsoftware.core.di.management.HasDaggerComponent
import com.sedsoftware.core.di.management.HasInject
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.screens.intro.base.controller.IntroBaseController
import com.sedsoftware.screens.intro.base.databinding.FragmentIntroBaseBinding
import com.sedsoftware.screens.intro.base.di.IntroBaseComponent
import javax.inject.Inject

class IntroBaseFragment : BaseFragment(), HasDaggerComponent<IntroBaseComponent>, HasInject {

    companion object {
        fun newInstance(): IntroBaseFragment =
            IntroBaseFragment()
    }

    private val binding: FragmentIntroBaseBinding get() = _binding!!
    private var _binding: FragmentIntroBaseBinding? = null

    @Inject
    lateinit var controller: IntroBaseController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentIntroBaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controller.onViewCreated(
            introBaseView = IntroBaseViewImpl(binding),
            viewLifecycle = viewLifecycleOwner.lifecycle.asMviLifecycle(),
            errorHandlerView = this
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getComponent(): IntroBaseComponent {
        val activityTools = DaggerComponentManager.get<ActivityToolsProvider>()
        return IntroBaseComponent.Initializer.init(activityTools)
    }

    override fun getComponentKey(): String =
        this::class.java.simpleName

    override fun inject() {
        DaggerComponentManager
            .get<IntroBaseComponent>()
            .inject(this)
    }
}
