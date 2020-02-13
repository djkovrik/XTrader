package com.sedsoftware.core.presentation.base

import androidx.appcompat.app.AppCompatActivity
import com.sedsoftware.core.presentation.CanHandleBackPressed
import kotlinx.coroutines.Job
import java.util.LinkedList
import java.util.Queue

abstract class BaseActivity : AppCompatActivity(), CanHandleBackPressed {

//    abstract val appProvider: AppProvider
//    abstract val activityToolsProvider: ActivityToolsProvider

    protected val notificationQueue: Queue<String> = LinkedList()
    protected var notificationJob: Job? = null
    protected var isNotificationVisible = false

    private var backPressFragment: BaseFragment? = null

    override fun setSelectedFragment(fragment: BaseFragment) {
        backPressFragment = fragment
    }

    override fun onBackPressed() {
        if (backPressFragment?.onBackPressed() == false) {
            super.onBackPressed()
        }
    }
}
