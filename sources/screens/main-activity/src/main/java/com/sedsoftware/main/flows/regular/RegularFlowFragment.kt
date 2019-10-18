package com.sedsoftware.main.flows.regular

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sedsoftware.core.di.qualifier.RegularFlow
import com.sedsoftware.core.presentation.base.FlowFragment
import com.sedsoftware.main.Screens
import com.sedsoftware.screens.main.R
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

class RegularFlowFragment : FlowFragment() {

    companion object {
        fun newInstance(): RegularFlowFragment = RegularFlowFragment()
    }

    override val layoutResId: Int = R.layout.layout_container
    override val launchScreen: SupportAppScreen = Screens.Market

    @Inject
    @RegularFlow
    lateinit var router: Router

    @Inject
    @RegularFlow
    override lateinit var navigatorHolder: NavigatorHolder

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
}
