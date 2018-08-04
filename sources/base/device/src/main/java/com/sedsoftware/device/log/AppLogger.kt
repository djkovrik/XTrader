package com.sedsoftware.device.log

import com.sedsoftware.core.device.Logger
import timber.log.Timber

class AppLogger : Logger {

    override fun d(message: String) {
        Timber.d(message)
    }

    override fun e(message: String) {
        Timber.e(message)
    }

    override fun i(message: String) {
        Timber.i(message)
    }

    override fun v(message: String) {
        Timber.v(message)
    }

    override fun w(message: String) {
        Timber.w(message)
    }
}
