package com.sedsoftware.core.presentation.base

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Job
import java.util.LinkedList
import java.util.Queue

abstract class BaseActivity : AppCompatActivity() {

    protected val notificationQueue: Queue<String> = LinkedList()
    protected var notificationJob: Job? = null
    protected var isNotificationVisible = false
}
