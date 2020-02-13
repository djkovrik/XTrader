package com.sedsoftware.core.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sedsoftware.core.presentation.R
import com.sedsoftware.core.presentation.extension.setLaunchScreen
import com.sedsoftware.core.presentation.navigation.TabCiceroneHolder
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

abstract class BaseTabFragment : Fragment() {

    abstract val launchScreen: SupportAppScreen

    private val navigator: Navigator by lazy {
        object : SupportAppNavigator(requireActivity(), childFragmentManager, R.id.container) {
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
    lateinit var tabCiceroneHolder: TabCiceroneHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (childFragmentManager.fragments.isEmpty()) {
            navigator.setLaunchScreen(launchScreen)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.layout_container, container, false)

    override fun onResume() {
        super.onResume()
        getCicerone().navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        getCicerone().navigatorHolder.removeNavigator()
        super.onPause()
    }

    private fun getContainerTag(): String =
        this.javaClass.simpleName

    private fun getCicerone(): Cicerone<Router> =
        tabCiceroneHolder.getCicerone(getContainerTag())

}
