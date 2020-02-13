package com.sedsoftware.core.di.management

object DaggerComponentManager {

    private val components = mutableMapOf<String, Any>()

    fun add(key: String, component: Any) {
        components[key] = component
    }

    fun get(key: String): Any? =
        components[key]

    fun remove(key: String) {
        components.remove(key)
    }

    fun find(predicate: (Any) -> Boolean): Any? =
        components.values.find { predicate(it) }
}
