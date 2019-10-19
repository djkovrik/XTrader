package com.sedsoftware.screens.intro

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.StartingFlowToolsProvider
import com.sedsoftware.core.di.qualifier.StartingFlow
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.extension.viewModel
import com.sedsoftware.screens.intro.di.IntroScreenComponent
import kotlinx.android.synthetic.main.fragment_intro_screen.*
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import javax.inject.Inject

class IntroScreenFragment : BaseFragment(), IHasComponent<IntroScreenComponent> {

    companion object {
        fun newInstance(): IntroScreenFragment = IntroScreenFragment()
    }

    override val layoutResId: Int = R.layout.fragment_intro_screen

    @Inject
    @StartingFlow
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var introScreenViewModel: IntroScreenViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        XInjectionManager
            .bindComponent(this)
            .inject(this)
    }

    override fun getComponent(): IntroScreenComponent {
        val startingFlowToolsProvider =
            XInjectionManager.findComponent { it is StartingFlowToolsProvider } as StartingFlowToolsProvider

        return IntroScreenComponent.Initializer.init(startingFlowToolsProvider)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        introScreenViewModel = viewModel(viewModelFactory) {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener { introScreenViewModel.switchToRegularFlow() }
    }
}
