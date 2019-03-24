package com.sedsoftware.core.presentation.extension

import androidx.lifecycle.viewModelScope
import com.sedsoftware.core.presentation.base.BaseViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

fun BaseViewModel.launch(block: () -> Unit) =
    viewModelScope.launch { block.invoke() }

fun <T> BaseViewModel.async(block: () -> T): Deferred<T> =
    viewModelScope.async { block.invoke() }