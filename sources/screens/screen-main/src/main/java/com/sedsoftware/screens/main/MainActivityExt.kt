package com.sedsoftware.screens.main

import android.util.SparseArray
import androidx.core.util.forEach
import androidx.core.util.set
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.absoluteValue

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

fun MainActivity.setupWithNavController(
        bottomNavigationView: BottomNavigationView,
        navGraphIds: List<Int>,
        containerId: Int
): LiveData<NavController> {

    // Map of tags
    val graphIdToTagMap = SparseArray<String>()
    // Result. Mutable live data with the selected controlled
    val selectedNavController = MutableLiveData<NavController>()

    var firstFragmentGraphId = 0

    // First create a NavHostFragment for each NavGraph ID
    navGraphIds.forEachIndexed { index, navGraphId ->
        val fragmentTag = getFragmentTag(index)

        // Find or create the Navigation host fragment
        val navHostFragment = obtainNavHostFragment(fragmentTag, navGraphId, containerId)

        // Obtain its id
        val graphId = navHostFragment.navController.graph.id

        if (index == 0) {
            firstFragmentGraphId = graphId
        }

        // Save to the map
        graphIdToTagMap[graphId] = fragmentTag

        // Attach or detach nav host fragment depending on whether it's the selected item.
        if (bottomNavigationView.selectedItemId == graphId) {
            // Update livedata with the selected graph
            selectedNavController.value = navHostFragment.navController
            attachNavHostFragment(navHostFragment, index == 0)
        } else {
            detachNavHostFragment(navHostFragment)
        }
    }

    // Now connect selecting an item with swapping Fragments
    var selectedItemTag = graphIdToTagMap[bottomNavigationView.selectedItemId]
    val firstFragmentTag = graphIdToTagMap[firstFragmentGraphId]
    var isOnFirstFragment = selectedItemTag == firstFragmentTag

    // When a navigation item is selected
    bottomNavigationView.setOnNavigationItemSelectedListener { item ->
        // Don't do anything if the state is state has already been saved.
        if (supportFragmentManager.isStateSaved) {
            false
        } else {
            val newlySelectedItemTag = graphIdToTagMap[item.itemId]
            if (selectedItemTag != newlySelectedItemTag) {
                // Pop everything above the first fragment (the "fixed start destination")
                supportFragmentManager.popBackStack(
                        firstFragmentTag,
                        FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
                val selectedFragment = supportFragmentManager.findFragmentByTag(newlySelectedItemTag)
                        as NavHostFragment

                // Exclude the first fragment tag because it's always in the back stack.
                if (firstFragmentTag != newlySelectedItemTag) {
                    // Commit a transaction that cleans the back stack and adds the first fragment
                    // to it, creating the fixed started destination.
                    supportFragmentManager.beginTransaction()
                            .attach(selectedFragment)
                            .setPrimaryNavigationFragment(selectedFragment)
                            .apply {
                                // Detach all other Fragments
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

    // Finally, ensure that we update our BottomNavigationView when the back stack changes
    supportFragmentManager.addOnBackStackChangedListener {
        if (!isOnFirstFragment && !supportFragmentManager.isOnBackStack(firstFragmentTag)) {
            bottomNavigationView.selectedItemId = firstFragmentGraphId
        }

        // Reset the graph if the currentDestination is not valid (happens when the back
        // stack is popped after using the back button).
        selectedNavController.value?.let { controller ->
            if (controller.currentDestination == null) {
                controller.navigate(controller.graph.id)
            }
        }
    }
    return selectedNavController
}

fun MainActivity.getFragmentTag(index: Int, navigation: Boolean = true) =
        if (navigation) "bottomNavigation#$index" else "fragment${index.absoluteValue}"
