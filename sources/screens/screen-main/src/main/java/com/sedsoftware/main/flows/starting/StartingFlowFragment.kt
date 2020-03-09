package com.sedsoftware.main.flows.starting

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.management.DaggerComponentManager
import com.sedsoftware.core.di.management.HasDaggerComponent
import com.sedsoftware.core.di.management.HasInject
import com.sedsoftware.core.presentation.base.FlowFragment
import com.sedsoftware.core.presentation.navigation.AppFlow
import com.sedsoftware.core.presentation.navigation.MainCiceroneHolder
import com.sedsoftware.main.Screens
import com.sedsoftware.main.flows.starting.di.StartingFlowComponent
import com.sedsoftware.screens.main.R
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

class StartingFlowFragment : FlowFragment(), HasDaggerComponent<StartingFlowComponent>, HasInject {

    companion object {
        fun newInstance(): StartingFlowFragment = StartingFlowFragment()
    }

    override val layoutResId: Int = R.layout.layout_container

    override val launchScreen: SupportAppScreen = Screens.Empty

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
    lateinit var ciceroneHolder: MainCiceroneHolder

    private val router: Router by lazy {
        ciceroneHolder.getRouter(AppFlow.STARTING)
    }

    private val navigatorHolder: NavigatorHolder by lazy {
        ciceroneHolder.getNavigatorHolder(AppFlow.STARTING)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun getComponent(): StartingFlowComponent {
        val activityTools = DaggerComponentManager.get<ActivityToolsProvider>()
        return StartingFlowComponent.Initializer.init(activityTools)
    }

    override fun getComponentKey(): String =
        this::class.java.simpleName

    override fun inject() {
        DaggerComponentManager
            .get<StartingFlowComponent>()
            .inject(this)
    }
}
