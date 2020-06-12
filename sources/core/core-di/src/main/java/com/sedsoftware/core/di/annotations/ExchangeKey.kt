package com.sedsoftware.core.di.annotations

import com.sedsoftware.core.domain.ExchangeType
import dagger.MapKey
import kotlin.annotation.AnnotationRetention.RUNTIME

@MapKey
@Retention(RUNTIME)
annotation class ExchangeKey(val value: ExchangeType)
