package com.sedsoftware.core.di.qualifier

import com.sedsoftware.core.domain.ExchangeType
import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class ForExchange(val value: ExchangeType)
