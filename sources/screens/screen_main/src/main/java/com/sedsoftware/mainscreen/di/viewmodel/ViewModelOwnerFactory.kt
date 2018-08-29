package com.sedsoftware.mainscreen.di.viewmodel

import com.sedsoftware.core.factory.ViewModelFactory
import com.sedsoftware.core.marker.ViewModelOwner
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
@Suppress("UNCHECKED_CAST")
class ViewModelOwnerFactory @Inject constructor(
    private val creators: Map<Class<out ViewModelOwner>,
    @JvmSuppressWildcards Provider<ViewModelOwner>>
) : ViewModelFactory {

    override fun <T : ViewModelOwner> create(modelClass: Class<T>): T {
        val creator =
            creators[modelClass] ?: creators.asIterable().firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
            ?: throw IllegalArgumentException("Unknown ViewModelOwner class $modelClass")

        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
