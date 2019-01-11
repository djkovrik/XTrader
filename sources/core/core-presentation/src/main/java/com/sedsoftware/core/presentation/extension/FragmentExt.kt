package com.sedsoftware.core.presentation.extension

import androidx.annotation.AttrRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.ViewModelProviders
import com.sedsoftware.core.navigation.destination.DestinationFactory
import com.sedsoftware.core.presentation.base.BaseFragment

val BaseFragment.destinations: DestinationFactory
    get() {
        val factory = destinationFactories[this::class.java]
                ?: destinationFactories.asIterable().firstOrNull { this::class.java.isAssignableFrom(it.key) }?.value
                ?: throw IllegalArgumentException("Destination factory for ${this::class.java} not found")

        return try {
            factory.get() as DestinationFactory
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

inline fun <reified T : ViewModel> Fragment.viewModel(factory: Factory, body: T.() -> Unit): T {
    val viewModel = ViewModelProviders.of(this, factory)[T::class.java]
    viewModel.body()
    return viewModel
}

fun Fragment.string(@StringRes resId: Int): String =
    context?.string(resId).orEmpty()

fun Fragment.setBackgroundColor(@AttrRes colorAttrId: Int) {
    activity?.let { it.window?.decorView?.setBackgroundColor(it.colorFromAttr(colorAttrId)) }
}
