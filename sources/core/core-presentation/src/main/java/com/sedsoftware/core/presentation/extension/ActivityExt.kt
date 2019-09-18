package com.sedsoftware.core.presentation.extension

import android.app.Activity
import androidx.annotation.ColorRes
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> FragmentActivity.viewModel(
    factory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T {
    val viewModel = ViewModelProvider(this, factory).get(T::class.java)
    viewModel.body()
    return viewModel
}

fun Activity.setBackgroundColor(@ColorRes colorId: Int) {
    window?.decorView?.setBackgroundColor(color(colorId))
}
