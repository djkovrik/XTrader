package com.sedsoftware.core.presentation.extension

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.Factory
import com.sedsoftware.core.utils.extension.orZero

inline fun <reified T : ViewModel> Fragment.viewModel(factory: Factory, body: T.() -> Unit): T {
    val viewModel = ViewModelProvider(this, factory).get(T::class.java)
    viewModel.body()
    return viewModel
}

fun Fragment.string(@StringRes resId: Int, vararg formatArgs: Any? = arrayOfNulls(0)): String =
    context?.getString(resId, formatArgs).orEmpty()

fun Fragment.color(@ColorRes colorId: Int) =
    context?.let { ContextCompat.getColor(it, colorId) }.orZero()
