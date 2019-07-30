package com.sedsoftware.screens.main

import android.util.SparseArray
import androidx.core.util.forEach
import androidx.core.util.set
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

// From Google's NavigationAdvancedSample
fun MainActivity.obtainNavHostFragment(tag: String, graphId: Int, containerId: Int): NavHostFragment {

    val existingFragment = supportFragmentManager.findFragmentByTag(tag) as NavHostFragment?
    existingFragment?.let { return it }

    val navHostFragment = NavHostFragment.create(graphId)
    supportFragmentManager.beginTransaction()
        .add(containerId, navHostFragment, tag)
        .commitNow()
    return navHostFragment
}

fun MainActivity.attachNavHostFragment(fragment: NavHostFragment, isPrimary: Boolean) {
    supportFragmentManager.beginTransaction()
        .attach(fragment)
        .apply {
            if (isPrimary) {
                setPrimaryNavigationFragment(fragment)
            }
        }
        .commitNow()
}

fun MainActivity.detachNavHostFragment(fragment: NavHostFragment) {
    supportFragmentManager.beginTransaction()
        .detach(fragment)
        .commitNow()
}


fun FragmentManager.isOnBackStack(backStackName: String): Boolean {
    val backStackCount = backStackEntryCount
    for (index in 0 until backStackCount) {
        if (getBackStackEntryAt(index).name == backStackName) {
            return true
        }
    }
    return false
}

@Suppress("ComplexMethod", "LongMethod")
fun MainActivity.setupWithNavController(
    bottomNavigationView: BottomNavigationView,
    navGraphIds: List<Int>,
    containerId: Int
): MutableLiveData<NavController> {

    val graphIdToTagMap = SparseArray<String>()
    val selectedNavController = MutableLiveData<NavController>()
    var firstFragmentGraphId = 0

    navGraphIds.forEachIndexed { index, navGraphId ->
        val fragmentTag = getFragmentTag(index)

        val navHostFragment = obtainNavHostFragment(fragmentTag, navGraphId, containerId)

        val graphId = navHostFragment.navController.graph.id

        if (index == MainActivity.DEFAULT_TAB_INDEX) {
            firstFragmentGraphId = graphId
        }

        graphIdToTagMap[graphId] = fragmentTag

        if (bottomNavigationView.selectedItemId == graphId) {
            selectedNavController.value = navHostFragment.navController
            attachNavHostFragment(navHostFragment, index == 0)
        } else {
            detachNavHostFragment(navHostFragment)
        }
    }

    var selectedItemTag = graphIdToTagMap[bottomNavigationView.selectedItemId]
    val firstFragmentTag = graphIdToTagMap[firstFragmentGraphId]
    var isOnFirstFragment = selectedItemTag == firstFragmentTag

    bottomNavigationView.setOnNavigationItemSelectedListener { item ->
        if (supportFragmentManager.isStateSaved) {
            false
        } else {
            val newlySelectedItemTag = graphIdToTagMap[item.itemId]
            if (selectedItemTag != newlySelectedItemTag) {
                supportFragmentManager.popBackStack(
                    firstFragmentTag,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
                val selectedFragment = supportFragmentManager.findFragmentByTag(newlySelectedItemTag)
                    as NavHostFragment

                if (firstFragmentTag != newlySelectedItemTag) {
                    supportFragmentManager.beginTransaction()
                        .attach(selectedFragment)
                        .setPrimaryNavigationFragment(selectedFragment)
                        .apply {
                            graphIdToTagMap.forEach { _, fragmentTagIter ->
                                if (fragmentTagIter != newlySelectedItemTag) {
                                    detach(supportFragmentManager.findFragmentByTag(firstFragmentTag)!!)
                                }
                            }
                        }
                        .addToBackStack(firstFragmentTag)
                        .setCustomAnimations(
                            R.anim.nav_default_enter_anim,
                            R.anim.nav_default_exit_anim,
                            R.anim.nav_default_pop_enter_anim,
                            R.anim.nav_default_pop_exit_anim
                        )
                        .setReorderingAllowed(true)
                        .commit()
                }
                selectedItemTag = newlySelectedItemTag
                isOnFirstFragment = selectedItemTag == firstFragmentTag
                selectedNavController.value = selectedFragment.navController
                true
            } else {
                false
            }
        }
    }

    supportFragmentManager.addOnBackStackChangedListener {
        if (!isOnFirstFragment && !supportFragmentManager.isOnBackStack(firstFragmentTag)) {
            bottomNavigationView.selectedItemId = firstFragmentGraphId
        }

        selectedNavController.value?.let { controller ->
            if (controller.currentDestination == null) {
                controller.navigate(controller.graph.id)
            }
        }
    }
    return selectedNavController
}

fun MainActivity.getFragmentTag(index: Int, bottomNavigation: Boolean = true) =
    if (bottomNavigation) "bottomNavigation#$index" else "fragment$index"
