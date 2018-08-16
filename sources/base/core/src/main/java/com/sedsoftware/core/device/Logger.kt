package com.sedsoftware.core.device

interface Logger {

    fun d(message: String)

    fun e(message: String)

    fun i(message: String)

    fun v(message: String)

    fun w(message: String)
}
