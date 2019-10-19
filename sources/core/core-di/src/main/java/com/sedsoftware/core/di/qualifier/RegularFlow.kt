package com.sedsoftware.core.di.qualifier

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.annotation.AnnotationTarget.PROPERTY_GETTER
import kotlin.annotation.AnnotationTarget.PROPERTY_SETTER
import kotlin.annotation.AnnotationTarget.VALUE_PARAMETER

@Qualifier
@Retention(RUNTIME)
@Target(FIELD, VALUE_PARAMETER, FUNCTION, PROPERTY_GETTER, PROPERTY_SETTER)
annotation class RegularFlow
