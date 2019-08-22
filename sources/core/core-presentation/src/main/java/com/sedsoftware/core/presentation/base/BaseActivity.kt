package com.sedsoftware.core.presentation.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.presentation.CanHandleBackPressed
import kotlinx.coroutines.Job
import java.util.LinkedList
import java.util.Queue
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), CanHandleBackPressed {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    protected val notificationQueue: Queue<String> = LinkedList()
    protected var notificationJob: Job? = null
    protected var isNotificationVisible = false

    private lateinit var currentFragment: BaseFragment

    override fun setSelectedFragment(fragment: BaseFragment) {
        currentFragment = fragment
    }

    override fun onBackPressed() {
        if (currentFragment.onBackPressed()) {
            // BackPressed event consumed
        } else {
            super.onBackPressed()
        }
    }
}
