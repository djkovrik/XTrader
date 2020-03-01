package com.sedsoftware.core.di.management

/**
 * Highlights that class which implements this interface can create Dagger 2 component
 *
 * @param <T> Component type
 */
interface HasDaggerComponent<T> {

    /**
     * Returns actual component which will be stored
     *
     * @return Initialised component
     */
    fun getComponent(): T

    /**
     * Returns key string which will be used to store the component
     *
     * @return Key value
     */
    fun getComponentKey(): String

    /**
     * Called before dagger component being destroyed
     */
    fun onComponentDestroyed() = Unit
}
