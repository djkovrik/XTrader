package com.sedsoftware.screens.intro.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.screens.intro.base.databinding.FragmentIntroBaseBinding

class IntroBaseFragment : BaseFragment() {

    companion object {
        fun newInstance(): IntroBaseFragment = IntroBaseFragment()
    }

    var binding: FragmentIntroBaseBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentIntroBaseBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

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
