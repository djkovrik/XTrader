package com.sedsoftware.main.flows.starting.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.qualifier.StartingFlow
import com.sedsoftware.core.di.scope.FlowScope
import javax.inject.Inject
import javax.inject.Provider

@FlowScope
@Suppress("UNCHECKED_CAST")
class StartingViewModelOwnerFactory @Inject constructor(
    @StartingFlow private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator =
            creators[modelClass]
                    ?: creators.asIterable().firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
                    ?: throw IllegalArgumentException("Unknown ViewModelOwner class $modelClass")

        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
