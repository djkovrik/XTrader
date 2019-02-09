package com.sedsoftware.screens.intro

import android.os.Bundle
import com.sedsoftware.core.di.coordinator.IntroCoordinator
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.extension.failure
import com.sedsoftware.core.presentation.extension.viewModel
import com.sedsoftware.core.utils.common.Failure
import com.sedsoftware.screens.intro.di.IntroScreenComponent
import com.sedsoftware.screens.intro.viewmodel.IntroDownloadsViewModel
import javax.inject.Inject

class IntroDownloadsFragment : BaseFragment() {

    @Inject
    lateinit var coordinator: IntroCoordinator

    private lateinit var introDownloadsViewModel: IntroDownloadsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        introDownloadsViewModel = viewModel(viewModelFactory) {
            failure(viewModelFailure, ::handleDownloadsFailure)
        }
    }

    override fun getLayoutResId(): Int =
        R.layout.fragment_intro_downloads

    override fun inject() {
        IntroScreenComponent.Initializer.init(parentActivityComponent)
            .inject(this@IntroDownloadsFragment)
    }

    private fun handleDownloadsFailure(failure: Failure?) {

    }
}
