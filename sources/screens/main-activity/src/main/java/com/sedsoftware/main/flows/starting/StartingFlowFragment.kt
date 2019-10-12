package com.sedsoftware.main.flows.starting

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sedsoftware.core.presentation.base.FlowFragment
import com.sedsoftware.main.Screens
import com.sedsoftware.main.flows.regular.di.RegularFlowComponent
import com.sedsoftware.screens.main.R
import me.vponomarenko.injectionmanager.IHasComponent
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command

class StartingFlowFragment : FlowFragment(), IHasComponent<RegularFlowComponent> {

    companion object {
        fun newInstance(): StartingFlowFragment =
            StartingFlowFragment()
    }

    override val layoutResId: Int = R.layout.layout_container

    override val launchScreen: SupportAppScreen = Screens.Intro

    override val navigator: Navigator by lazy {
        object : SupportAppNavigator(this.activity, childFragmentManager, R.id.container) {
            override fun activityBack() {
                router.exit()
            }

            override fun setupFragmentTransaction(
                command: Command?,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                fragmentTransaction.setReorderingAllowed(true)
            }
        }
    }

    override fun getComponent(): RegularFlowComponent =
        RegularFlowComponent.Initializer.init(activityToolsProvider)
}
