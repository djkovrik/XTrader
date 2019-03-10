package com.sedsoftware.screens.intro

import android.os.Bundle
import com.sedsoftware.core.di.coordinator.IntroCoordinator
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.extension.failure
import com.sedsoftware.core.presentation.extension.viewModel
import com.sedsoftware.core.utils.common.Failure
import com.sedsoftware.screens.intro.di.IntroScreenComponent
import com.sedsoftware.screens.intro.viewmodel.IntroScreenViewModel
import javax.inject.Inject

class IntroScreenFragment : BaseFragment() {

    @Inject
    lateinit var coordinator: IntroCoordinator

    private lateinit var introViewModel: IntroScreenViewModel

    override fun getLayoutResId(): Int =
        R.layout.fragment_intro_screen

    override fun inject() {
        IntroScreenComponent.Initializer.init(parentActivityComponent)
            .inject(this@IntroScreenFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        introViewModel = viewModel(viewModelFactory) {
            failure(viewModelFailure, ::handleDownloadsFailure)
        }
    }

    private fun handleDownloadsFailure(failure: Failure?) {

    }
}
