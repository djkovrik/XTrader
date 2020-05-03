package com.sedsoftware.core.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sedsoftware.core.domain.tools.CiceroneManager
import com.sedsoftware.core.presentation.R
import com.sedsoftware.core.presentation.databinding.LayoutContainerBinding
import com.sedsoftware.core.presentation.extension.setLaunchScreen
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
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
    lateinit var ciceroneManager: CiceroneManager

    private val binding: LayoutContainerBinding get() = _binding!!
    private var _binding: LayoutContainerBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (childFragmentManager.fragments.isEmpty()) {
            navigator.setLaunchScreen(launchScreen)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = LayoutContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getNavigatorHolder(): NavigatorHolder =
        ciceroneManager.getNavigatorHolder(getContainerTag())

    private fun getContainerTag(): String =
        this.javaClass.simpleName
}
