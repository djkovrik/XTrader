package com.sedsoftware.screens.intro.base.view

import android.os.Bundle
import android.view.View
import com.arkivanov.mvikotlin.extensions.androidx.lifecycle.asMviLifecycle
import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.management.DaggerComponentManager
import com.sedsoftware.core.di.management.HasDaggerComponent
import com.sedsoftware.core.di.management.HasInject
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.viewbinding.viewBinding
import com.sedsoftware.screens.intro.base.R
import com.sedsoftware.screens.intro.base.controller.IntroBaseController
import com.sedsoftware.screens.intro.base.databinding.FragmentIntroBaseBinding
import com.sedsoftware.screens.intro.base.di.IntroBaseComponent
import javax.inject.Inject

class IntroBaseFragment :
    BaseFragment(R.layout.fragment_intro_base), HasDaggerComponent<IntroBaseComponent>, HasInject {

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

    override fun getComponent(): IntroBaseComponent {
        val activityTools = DaggerComponentManager.get<ActivityToolsProvider>()
        return IntroBaseComponent.Initializer.init(activityTools)
    }

    override fun inject() {
        DaggerComponentManager
            .get<IntroBaseComponent>()
            .inject(this)
    }
}
