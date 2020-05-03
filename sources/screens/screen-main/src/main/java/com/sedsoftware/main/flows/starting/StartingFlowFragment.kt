package com.sedsoftware.main.flows.starting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sedsoftware.core.di.ActivityToolsProvider
import com.sedsoftware.core.di.management.DaggerComponentManager
import com.sedsoftware.core.di.management.HasDaggerComponent
import com.sedsoftware.core.di.management.HasInject
import com.sedsoftware.core.domain.tools.CiceroneManager
import com.sedsoftware.core.presentation.base.FlowFragment
import com.sedsoftware.core.presentation.databinding.LayoutContainerBinding
import com.sedsoftware.main.Screens
import com.sedsoftware.main.flows.AppFlow
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

    private val binding: LayoutContainerBinding get() = _binding!!
    private var _binding: LayoutContainerBinding? = null

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = LayoutContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getComponent(): StartingFlowComponent {
        val activityTools = DaggerComponentManager.get<ActivityToolsProvider>()
        return StartingFlowComponent.Initializer.init(activityTools)
    }

    override fun inject() {
        DaggerComponentManager
            .get<StartingFlowComponent>()
            .inject(this)
    }
}
