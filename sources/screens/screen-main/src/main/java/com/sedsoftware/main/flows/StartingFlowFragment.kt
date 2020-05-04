package com.sedsoftware.main.flows

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sedsoftware.core.di.management.DaggerComponentManager
import com.sedsoftware.core.di.management.HasInject
import com.sedsoftware.core.domain.tools.CiceroneManager
import com.sedsoftware.core.presentation.base.FlowFragment
import com.sedsoftware.main.Screens
import com.sedsoftware.main.di.MainActivityComponent
import com.sedsoftware.screens.main.R
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

class StartingFlowFragment : FlowFragment(R.layout.layout_container), HasInject {

    companion object {
        fun newInstance(): StartingFlowFragment = StartingFlowFragment()
    }

    override val launchScreen: SupportAppScreen = Screens.IntroBase

    override val navigator: Navigator by lazy {
        object : SupportAppNavigator(requireActivity(), childFragmentManager, R.id.container) {
            override fun activityBack() {
                router.exit()
            }

            override fun setupFragmentTransaction(
                command: Command,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                fragmentTransaction.setReorderingAllowed(true)
            }
        }
    }

    @Inject
    lateinit var ciceroneManager: CiceroneManager

    private val router: Router by lazy {
        ciceroneManager.getRouter(AppFlow.STARTING)
    }

    private val navigatorHolder: NavigatorHolder by lazy {
        ciceroneManager.getNavigatorHolder(AppFlow.STARTING)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun inject() {
        DaggerComponentManager
            .get<MainActivityComponent>()
            .inject(this)
    }
}
