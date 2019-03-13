package com.sedsoftware.core.tests

import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.dsl.LifecycleAware
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.lifecycle.MemoizedValue

fun <R> LifecycleAware.memoizedBlocking(
    mode: CachingMode = defaultCachingMode,
    factory: suspend () -> R,
    destructor: suspend (R) -> Unit
): MemoizedValue<R> =
    memoized(mode, factory = { runBlocking { factory() } }, destructor = { runBlocking { destructor(it) } })

fun LifecycleAware.beforeBlocking(block: suspend () -> Unit) =
    beforeGroup { runBlocking { block() } }

fun LifecycleAware.beforeEachBlocking(block: suspend () -> Unit) =
    beforeEachTest { runBlocking { block() } }

fun LifecycleAware.afterBlocking(block: suspend () -> Unit) =
    afterGroup { runBlocking { block() } }

fun LifecycleAware.afterEachBlocking(block: suspend () -> Unit) =
    afterEachTest { runBlocking { block() } }
