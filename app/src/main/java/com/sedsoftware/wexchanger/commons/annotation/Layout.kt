package com.sedsoftware.wexchanger.commons.annotation

import android.support.annotation.LayoutRes
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS

@Retention(RUNTIME)
@Target(CLASS)
annotation class Layout(@LayoutRes val value: Int)
