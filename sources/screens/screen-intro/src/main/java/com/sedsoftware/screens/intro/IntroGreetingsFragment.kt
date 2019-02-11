package com.sedsoftware.screens.intro

import com.sedsoftware.core.di.coordinator.IntroCoordinator
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.screens.intro.di.IntroScreenComponent
import javax.inject.Inject

class IntroGreetingsFragment : BaseFragment() {

    @Inject
    lateinit var coordinator: IntroCoordinator

    override fun getLayoutResId(): Int =
        R.layout.fragment_intro_greetings

    override fun inject() {
        IntroScreenComponent.Initializer.init(parentActivityComponent)
            .inject(this@IntroGreetingsFragment)
    }
}
