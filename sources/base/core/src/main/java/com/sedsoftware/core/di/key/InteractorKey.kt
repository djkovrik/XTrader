package com.sedsoftware.core.di.key

import com.sedsoftware.core.interactor.Interactor
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
internal annotation class InteractorKey(val value: KClass<out Interactor>)
