package com.sedsoftware.core.utils.type

class SingleEvent<out T>(private val content: T) {

    private var hasBeenHandled = false

    fun getIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}
