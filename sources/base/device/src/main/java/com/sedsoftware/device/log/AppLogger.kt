package com.sedsoftware.device.log

import com.sedsoftware.coreapi.device.Logger
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppLogger @Inject constructor() : Logger {

    override fun d(message: String) {
        Timber.asTree().d(message)
    }

    override fun e(message: String) {
        Timber.asTree().e(message)
    }

    override fun i(message: String) {
        Timber.asTree().i(message)
    }

    override fun v(message: String) {
        Timber.asTree().v(message)
    }

    override fun w(message: String) {
        Timber.asTree().w(message)
    }
}
