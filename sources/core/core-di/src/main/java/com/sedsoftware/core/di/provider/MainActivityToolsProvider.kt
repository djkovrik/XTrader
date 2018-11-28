package com.sedsoftware.core.di.provider

import androidx.fragment.app.Fragment
import com.sedsoftware.core.di.holder.ActivityComponentHolder
import com.sedsoftware.core.navigation.factory.NavDirectionsFactory
import javax.inject.Provider


interface MainActivityToolsProvider : ViewModelFactoryProvider {
    fun provideDirectionsFactoryMap(): Map<Class<out Fragment>, @JvmSuppressWildcards Provider<NavDirectionsFactory>>

    fun inject(activity: ActivityComponentHolder)
}
