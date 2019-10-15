package com.sedsoftware.main.flows.starting

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sedsoftware.core.presentation.base.FlowFragment
import com.sedsoftware.main.Screens
import com.sedsoftware.main.flows.Flows
import com.sedsoftware.main.flows.starting.di.StartingFlowComponent
import com.sedsoftware.screens.main.R
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject
import javax.inject.Named

class StartingFlowFragment : FlowFragment(), IHasComponent<StartingFlowComponent> {

    companion object {
        fun newInstance(): StartingFlowFragment = StartingFlowFragment()
    }

    override val layoutResId: Int = R.layout.layout_container

    override val launchScreen: SupportAppScreen = Screens.Intro

    @Inject
    @Named(Flows.STARTING)
    override lateinit var navigatorHolder: NavigatorHolder

    @Inject
    @Named(Flows.STARTING)
    lateinit var router: Router

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        XInjectionManager
            .bindComponent(this)
            .inject(this)
    }

    override fun getComponent(): StartingFlowComponent =
        StartingFlowComponent.Initializer.init(activityToolsProvider)
}
