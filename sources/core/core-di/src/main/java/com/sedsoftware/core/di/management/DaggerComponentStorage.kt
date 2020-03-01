package com.sedsoftware.core.di.management

internal class DaggerComponentStorage {

    private val components = mutableMapOf<String, Any?>()

    val values: MutableCollection<Any?>
        get() = components.values

    fun add(key: String, component: Any?) {
        components[key] = component
    }

    fun get(key: String): Any? =
        components[key]

    fun remove(key: String) {
        components.remove(key)
    }
}
