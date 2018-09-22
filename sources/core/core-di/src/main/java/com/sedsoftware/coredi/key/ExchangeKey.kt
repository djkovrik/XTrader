package com.sedsoftware.coredi.key

import com.sedsoftware.coreentity.ExchangeType
import dagger.MapKey

@MapKey
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class ExchangeKey(val value: ExchangeType)
