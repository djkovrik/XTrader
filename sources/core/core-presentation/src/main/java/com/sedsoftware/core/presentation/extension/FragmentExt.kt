package com.sedsoftware.core.presentation.extension

import androidx.annotation.AttrRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.ViewModelProviders
import com.sedsoftware.core.navigation.factory.NavDirectionsFactory
import com.sedsoftware.core.presentation.base.BaseFragment
import javax.inject.Provider

inline fun <reified T : ViewModel> Fragment.viewModel(factory: Factory, body: T.() -> Unit): T {
    val viewModel = ViewModelProviders.of(this, factory)[T::class.java]
    viewModel.body()
    return viewModel
}

fun Fragment.navDirectionsFactory(
    factories: Map<Class<out Fragment>, Provider<NavDirectionsFactory>>,
    fragmentClass: Class<out BaseFragment>
): NavDirectionsFactory {

    val factory = factories[fragmentClass]
            ?: factories.asIterable().firstOrNull { fragmentClass.isAssignableFrom(it.key) }?.value
            ?: throw IllegalArgumentException("Factory for $fragmentClass not found")

    return try {
        factory.get() as NavDirectionsFactory
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}

fun Fragment.string(@StringRes resId: Int): String =
    context?.string(resId).orEmpty()

/**
 * Sets window background color via decorView to prevent overdraw.
 *
 * @param colorAttrId Color reference.
 */
fun Fragment.setBackgroundColor(@AttrRes colorAttrId: Int) {
    activity?.let { it.window?.decorView?.setBackgroundColor(it.colorFromAttr(colorAttrId)) }
}
