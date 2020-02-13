package com.sedsoftware.core.di.qualifier

import com.sedsoftware.core.domain.AppFlow
import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class ForFlow(val value: AppFlow)
