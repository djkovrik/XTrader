package com.sedsoftware.core.presentation.base

import androidx.appcompat.app.AppCompatActivity
import com.sedsoftware.core.di.App
import com.sedsoftware.core.di.AppProvider
import kotlinx.coroutines.Job
import java.util.LinkedList
import java.util.Queue

abstract class BaseActivity : AppCompatActivity() {

    protected val appComponent: AppProvider
        get() = (applicationContext as App).getAppComponent()

    protected val notificationQueue: Queue<String> = LinkedList()
    protected var notificationJob: Job? = null
    protected var isNotificationVisible = false
}
