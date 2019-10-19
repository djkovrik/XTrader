package com.sedsoftware.core.presentation.base

import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.qualifier.StartingFlow
import javax.inject.Inject

abstract class BaseStartingFragment : BaseFragment() {

    @Inject
    @StartingFlow
    lateinit var viewModelFactory: ViewModelProvider.Factory
}
