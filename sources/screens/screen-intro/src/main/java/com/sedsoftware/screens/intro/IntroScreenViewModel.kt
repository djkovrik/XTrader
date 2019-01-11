package com.sedsoftware.screens.intro

import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.presentation.base.BaseViewModel
import javax.inject.Inject

class IntroScreenViewModel @Inject constructor(
    private val exchanges: Set<@JvmSuppressWildcards Exchange>
) : BaseViewModel()
