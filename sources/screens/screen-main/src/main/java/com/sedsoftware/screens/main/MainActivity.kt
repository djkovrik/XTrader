package com.sedsoftware.screens.main

import android.os.Bundle
import android.util.SparseArray
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.forEach
import androidx.core.util.set
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.delegate.NavigationFlowDelegate
import com.sedsoftware.core.di.delegate.SnackbarDelegate
import com.sedsoftware.core.di.holder.ActivityToolsHolder
import com.sedsoftware.core.di.provider.MainActivityToolsProvider
import com.sedsoftware.core.navigation.NavControllerHolder
import com.sedsoftware.core.presentation.SwipeToDismissTouchListener
import com.sedsoftware.core.presentation.SwipeToDismissTouchListener.DismissCallbacks
import com.sedsoftware.core.presentation.extension.addEndAction
import com.sedsoftware.core.presentation.extension.launch
import com.sedsoftware.core.presentation.extension.setBackgroundColor
import com.sedsoftware.screens.main.di.MainActivityComponent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import java.util.LinkedList
import java.util.Queue
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ActivityToolsHolder, NavigationFlowDelegate, SnackbarDelegate {

    @Inject
    lateinit var navControllerHolder: NavControllerHolder

    private val mainActivityComponent: MainActivityComponent by lazy {
        val appComponent = (applicationContext as App).getAppComponent()
        MainActivityComponent.Initializer.init(appComponent, this, this)
    }

    private val navGraphIds: List<Int> = listOf(
        R.navigation.navigation_wallet,
        R.navigation.navigation_orders,
        R.navigation.navigation_market,
        R.navigation.navigation_tracker,
        R.navigation.navigation_tools
    )

    private val notificationQueue: Queue<String> = LinkedList()

    private var introNavHostFragment: NavHostFragment? = null
    private var currentNavController: LiveData<NavController>? = null
    private var notificationJob: Job? = null

    private var topNotificationTranslation = 0f
    private var bottomNavigationViewTranslation = 0f

    private var isNotificationVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivityComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setBackgroundColor(R.color.colorBackground)

        setupTopNotification()
        setupBottomNavigationInitial()

        introNavHostFragment = obtainNavHostFragment(
            tag = getFragmentTag(-1),
            graphId = R.navigation.navigation_intro,
            containerId = R.id.nav_host_container
        )

        introNavHostFragment?.let { fragment ->
            navControllerHolder.setNavController(fragment.navController)
            attachNavHostFragment(fragment, true)
        }
    }

    private fun setupTopNotification() {
        notification_top_text.post {
            topNotificationTranslation = -notification_top_text.measuredHeight.toFloat()
            notification_top_text.translationY = topNotificationTranslation
        }

        notification_top_text.setOnTouchListener(
            SwipeToDismissTouchListener(
                notification_top_text,
                object : DismissCallbacks {
                    override fun onDismiss(view: View) {
                        notification_top_text.translationY = topNotificationTranslation
                        notificationJob?.cancelChildren()
                        notificationQueue.clear()
                    }
                }
            ))
    }

    private fun setupBottomNavigationInitial() {
        bottom_navigation.post {
            bottomNavigationViewTranslation = bottom_navigation.measuredHeight.toFloat()
            bottom_navigation.translationY = bottomNavigationViewTranslation
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        currentNavController?.value?.let { navControllerHolder.setNavController(it) }
    }

    override fun onPause() {
        notificationJob?.cancel()
        navControllerHolder.removeNavController()
        super.onPause()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun getActivityToolsProvider(): MainActivityToolsProvider =
        mainActivityComponent

    override fun switchToMainFlow() {
        bottom_navigation.isVisible = true
        translateViewAnimated(bottom_navigation, 0f)
        introNavHostFragment?.let { detachNavHostFragment(it) }

        val controller = bottom_navigation.setupWithNavController(R.id.nav_host_container)

        // Whenever the selected controller changes, setup the action bar.
//        controller.observe(this, Observer { navController ->
//            setupActionBarWithNavController(navController)
//        })
        currentNavController = controller
    }

    override fun notifyOnTop(message: String) {
        if (!isNotificationVisible) {
            notification_top_text.text = message
            translateViewAnimated(notification_top_text, 0f) {
                isNotificationVisible = true
                hideNotification()
            }
        } else {
            notificationQueue.add(message)
        }
    }

    private fun hideNotification() {
        notificationJob = launch {
            delay(DELAY_BEFORE_HIDE)
            translateViewAnimated(notification_top_text, topNotificationTranslation) {
                isNotificationVisible = false
                if (notificationQueue.isNotEmpty()) {
                    notifyOnTop(notificationQueue.poll())
                }
            }
        }
    }

    private fun translateViewAnimated(view: View, translation: Float, finishedCallback: () -> Unit = {}) {
        view.animate()
            .translationY(translation)
            .setStartDelay(ANIMATION_DELAY)
            .setDuration(ANIMATION_DURATION)
            .setInterpolator(LinearInterpolator())
            .addEndAction { finishedCallback.invoke() }
            .start()
    }

    private fun obtainNavHostFragment(tag: String, graphId: Int, containerId: Int): NavHostFragment {

        val existingFragment = supportFragmentManager.findFragmentByTag(tag) as NavHostFragment?
        existingFragment?.let { return it }

        val navHostFragment = NavHostFragment.create(graphId)
        supportFragmentManager.beginTransaction()
            .add(containerId, navHostFragment, tag)
            .commitNow()
        return navHostFragment
    }

    private fun attachNavHostFragment(fragment: NavHostFragment, isPrimary: Boolean) {
        supportFragmentManager.beginTransaction()
            .attach(fragment)
            .apply {
                if (isPrimary) {
                    setPrimaryNavigationFragment(fragment)
                }
            }
            .commitNow()
    }

    private fun detachNavHostFragment(fragment: NavHostFragment) {
        supportFragmentManager.beginTransaction()
            .detach(fragment)
            .commitNow()
    }

    fun BottomNavigationView.setupWithNavController(containerId: Int): LiveData<NavController> {

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
            if (this.selectedItemId == graphId) {
                // Update livedata with the selected graph
                selectedNavController.value = navHostFragment.navController
                attachNavHostFragment(navHostFragment, index == 0)
            } else {
                detachNavHostFragment(navHostFragment)
            }
        }

        // Now connect selecting an item with swapping Fragments
        var selectedItemTag = graphIdToTagMap[this.selectedItemId]
        val firstFragmentTag = graphIdToTagMap[firstFragmentGraphId]
        var isOnFirstFragment = selectedItemTag == firstFragmentTag

        // When a navigation item is selected
        setOnNavigationItemSelectedListener { item ->
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
                this.selectedItemId = firstFragmentGraphId
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

    private fun FragmentManager.isOnBackStack(backStackName: String): Boolean {
        val backStackCount = backStackEntryCount
        for (index in 0 until backStackCount) {
            if (getBackStackEntryAt(index).name == backStackName) {
                return true
            }
        }
        return false
    }

    private fun getFragmentTag(index: Int) =
        if (index == -1) "introFragment" else "bottomNavigation#$index"

    private companion object {
        const val ANIMATION_DELAY = 100L
        const val ANIMATION_DURATION = 200L
        const val DELAY_BEFORE_HIDE = 3000L
    }
}
