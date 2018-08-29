package com.sedsoftware.core.di.key

import com.sedsoftware.core.marker.ViewModelOwner
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class ViewModelOwnerKey(val value: KClass<out ViewModelOwner>)
