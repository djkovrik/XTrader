package com.sedsoftware.screens.intro.base

import com.sedsoftware.core.presentation.base.BaseFragment

class IntroBaseFragment : BaseFragment() {

    companion object {
        fun newInstance(): IntroBaseFragment = IntroBaseFragment()
    }

    override val layoutResId: Int = R.layout.fragment_intro_base

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        downloadButton.setOnClickListener { introBaseViewModel.downloadCurrencyMap() }
//        nextButton.setOnClickListener { introBaseViewModel.navigateToExchanges() }
//    }
//
//    private fun observeDownloadLiveData(isCompleted: Boolean?) {
//        isCompleted?.let { nextButton.isEnabled = it }
//    }
}
