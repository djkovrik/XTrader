package com.sedsoftware.main.flows

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.chernovdmitriy.injectionholdercore.ComponentOwner
import com.sedsoftware.core.presentation.base.FlowFragment
import com.sedsoftware.main.Screens
import com.sedsoftware.main.flows.di.FlowComponent
import com.sedsoftware.screens.main.R
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command

class RegularFlowFragment : FlowFragment(), ComponentOwner<FlowComponent> {

    companion object {
        fun newInstance(): RegularFlowFragment =
            RegularFlowFragment()
    }

    override val layoutResId: Int = R.layout.layout_container

    override val launchScreen: SupportAppScreen = Screens.Market

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

    override fun inject(component: FlowComponent) =
        component.inject(this)

    override fun provideComponent(): FlowComponent =
        FlowComponent.Initializer.init(activityToolsProvider)

    override fun getComponentKey(): String = javaClass.name
}
