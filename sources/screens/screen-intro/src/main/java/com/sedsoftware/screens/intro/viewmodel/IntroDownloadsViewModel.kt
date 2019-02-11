package com.sedsoftware.screens.intro.viewmodel

import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.presentation.base.BaseViewModel
import javax.inject.Inject

class IntroDownloadsViewModel @Inject constructor(
    private val exchanges: Set<@JvmSuppressWildcards Exchange>
) : BaseViewModel()