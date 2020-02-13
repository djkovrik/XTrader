package com.sedsoftware.screens.intro.base

import android.os.Bundle
import android.view.View
import com.sedsoftware.core.presentation.base.BaseStartingFragment
import com.sedsoftware.core.presentation.extension.failure
import com.sedsoftware.core.presentation.extension.observe
import com.sedsoftware.core.presentation.extension.viewModel
import kotlinx.android.synthetic.main.fragment_intro_base.*

class IntroBaseFragment : BaseStartingFragment() {

    companion object {
        fun newInstance(): IntroBaseFragment = IntroBaseFragment()
    }

    override val layoutResId: Int = R.layout.fragment_intro_base

    private lateinit var introBaseViewModel: IntroBaseViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        introBaseViewModel = viewModel(viewModelFactory) {
            observe(downloadCompletedLiveData, ::observeDownloadLiveData)
            failure(viewModelFailure, ::observeFailures)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        downloadButton.setOnClickListener { introBaseViewModel.downloadCurrencyMap() }
        nextButton.setOnClickListener { introBaseViewModel.navigateToExchanges() }
    }

    private fun observeDownloadLiveData(isCompleted: Boolean?) {
        isCompleted?.let { nextButton.isEnabled = it }
    }
}
