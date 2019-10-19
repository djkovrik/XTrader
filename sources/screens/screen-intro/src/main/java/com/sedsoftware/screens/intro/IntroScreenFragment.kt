package com.sedsoftware.screens.intro

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.StartingFlowToolsProvider
import com.sedsoftware.core.di.qualifier.StartingFlow
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.screens.intro.di.IntroScreenComponent
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
}
