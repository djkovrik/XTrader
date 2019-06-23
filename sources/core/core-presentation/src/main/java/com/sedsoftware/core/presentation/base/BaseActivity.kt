package com.sedsoftware.core.presentation.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import kotlinx.coroutines.Job
import java.util.*
import javax.inject.Inject
import kotlin.math.absoluteValue

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val notificationQueue: Queue<String> = LinkedList()
    protected var notificationJob: Job? = null
    protected var isNotificationVisible = false

    protected fun obtainNavHostFragment(tag: String, graphId: Int, containerId: Int): NavHostFragment {

        val existingFragment = supportFragmentManager.findFragmentByTag(tag) as NavHostFragment?
        existingFragment?.let { return it }

        val navHostFragment = NavHostFragment.create(graphId)
        supportFragmentManager.beginTransaction()
                .add(containerId, navHostFragment, tag)
                .commitNow()
        return navHostFragment
    }

    protected fun attachNavHostFragment(fragment: NavHostFragment, isPrimary: Boolean) {
        supportFragmentManager.beginTransaction()
                .attach(fragment)
                .apply {
                    if (isPrimary) {
                        setPrimaryNavigationFragment(fragment)
                    }
                }
                .commitNow()
    }

    protected fun detachNavHostFragment(fragment: NavHostFragment) {
        supportFragmentManager.beginTransaction()
                .detach(fragment)
                .commitNow()
    }


    protected fun FragmentManager.isOnBackStack(backStackName: String): Boolean {
        val backStackCount = backStackEntryCount
        for (index in 0 until backStackCount) {
            if (getBackStackEntryAt(index).name == backStackName) {
                return true
            }
        }
        return false
    }

    protected fun getFragmentTag(index: Int, navigation: Boolean = true) =
            if (navigation) "bottomNavigation#$index" else "fragment${index.absoluteValue}"
}
