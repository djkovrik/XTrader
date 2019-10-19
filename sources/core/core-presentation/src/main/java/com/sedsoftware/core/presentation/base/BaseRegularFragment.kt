package com.sedsoftware.core.presentation.base

import androidx.lifecycle.ViewModelProvider
import com.sedsoftware.core.di.qualifier.RegularFlow
import javax.inject.Inject

abstract class BaseRegularFragment : BaseFragment() {

    @Inject
    @RegularFlow
    lateinit var viewModelFactory: ViewModelProvider.Factory
}
