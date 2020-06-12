package com.sedsoftware.main.flows

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sedsoftware.core.domain.tools.CiceroneManager
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.base.BaseTabFragment
import com.sedsoftware.core.presentation.viewbinding.viewBinding
import com.sedsoftware.main.Screens
import com.sedsoftware.screens.main.R
import com.sedsoftware.screens.main.databinding.FragmentFlowRegularBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

@AndroidEntryPoint
class RegularFlowFragment : BaseFragment(R.layout.fragment_flow_regular) {

    companion object {
        fun newInstance(): RegularFlowFragment = RegularFlowFragment()

        private val walletTab = Screens.WalletTabContainer
        private val ordersTab = Screens.OrdersTabContainer
        private val marketTab = Screens.MarketTabContainer
        private val trackerTab = Screens.TrackerTabContainer
        private val toolsTab = Screens.ToolsTabContainer

        private val DEFAULT_TAB = R.id.navigation_market
    }

    private val binding: FragmentFlowRegularBinding by viewBinding()

    private val navigator: Navigator by lazy {
        object : SupportAppNavigator(requireActivity(), childFragmentManager, R.id.regularFlowContainer) {
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
        ciceroneManager.getRouter(AppFlow.REGULAR)
    }

    private val navigatorHolder: NavigatorHolder by lazy {
        ciceroneManager.getNavigatorHolder(AppFlow.REGULAR)
    }

    private val currentTabFragment: BaseTabFragment?
        get() = childFragmentManager.fragments.firstOrNull { !it.isHidden } as? BaseTabFragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.navigation_wallet -> selectTab(walletTab)
                R.id.navigation_orders -> selectTab(ordersTab)
                R.id.navigation_tracker -> selectTab(trackerTab)
                R.id.navigation_tools -> selectTab(toolsTab)
                else -> selectTab(marketTab)
            }
            true
        }

        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = DEFAULT_TAB
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    private fun selectTab(tab: SupportAppScreen) {
        val currentFragment = currentTabFragment
        val newFragment = childFragmentManager.findFragmentByTag(tab.screenKey)

        if (currentFragment != null && newFragment != null && currentFragment == newFragment) return

        childFragmentManager.beginTransaction()
            .apply {
                if (newFragment == null) {
                    tab.fragment?.let { add(R.id.regularFlowContainer, it, tab.screenKey) }
                }

                currentFragment?.let { hide(it) }
                newFragment?.let { show(it) }
            }.commitNow()
    }
}
