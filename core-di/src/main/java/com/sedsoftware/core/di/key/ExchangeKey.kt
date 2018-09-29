package com.sedsoftware.core.di.key

import com.sedsoftware.core.domain.entity.ExchangeType
import dagger.MapKey

@MapKey
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class ExchangeKey(val value: ExchangeType)
