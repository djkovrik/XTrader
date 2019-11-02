package com.sedsoftware.screens.intro.base

import android.content.Context
import android.os.Bundle
import android.view.View
import com.sedsoftware.core.di.StartingFlowToolsProvider
import com.sedsoftware.core.presentation.base.BaseStartingFragment
import com.sedsoftware.core.presentation.extension.failure
import com.sedsoftware.core.presentation.extension.observe
import com.sedsoftware.core.presentation.extension.viewModel
import com.sedsoftware.screens.intro.base.di.IntroBaseComponent
import kotlinx.android.synthetic.main.fragment_intro_base.*
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager

class IntroBaseFragment : BaseStartingFragment(), IHasComponent<IntroBaseComponent> {

    companion object {
        fun newInstance(): IntroBaseFragment = IntroBaseFragment()
    }

    override val layoutResId: Int = R.layout.fragment_intro_base

    private lateinit var introBaseViewModel: IntroBaseViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        XInjectionManager
            .bindComponent(this)
            .inject(this)
    }

    override fun getComponent(): IntroBaseComponent {
        val startingFlowToolsProvider =
            XInjectionManager.findComponent { it is StartingFlowToolsProvider } as StartingFlowToolsProvider

        return IntroBaseComponent.Initializer.init(startingFlowToolsProvider)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        introBaseViewModel = viewModel(viewModelFactory) {
            observe(downloadingCompleted, ::observeDownloadLiveData)
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
