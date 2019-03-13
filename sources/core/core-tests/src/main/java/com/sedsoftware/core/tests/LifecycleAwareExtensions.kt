package com.sedsoftware.core.tests

import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.dsl.LifecycleAware
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.lifecycle.MemoizedValue

// Source: https://github.com/spekframework/spek/issues/426

fun <R> LifecycleAware.blockingMemoized(
    mode: CachingMode = CachingMode.SCOPE,
    factory: suspend () -> R
): MemoizedValue<R> =
    memoized(mode, factory = { runBlocking { factory() } })

fun <R> LifecycleAware.blockingMemoized(
    mode: CachingMode = CachingMode.SCOPE,
    factory: suspend () -> R,
    destructor: suspend (R) -> Unit
): MemoizedValue<R> =
    memoized(mode, factory = { runBlocking { factory() } }, destructor = { runBlocking { destructor(it) } })

fun LifecycleAware.blockingBefore(block: suspend () -> Unit) =
    beforeGroup { runBlocking { block() } }

fun LifecycleAware.blockingBeforeEach(block: suspend () -> Unit) =
    beforeEachTest { runBlocking { block() } }

fun LifecycleAware.blockingAfter(block: suspend () -> Unit) =
    afterGroup { runBlocking { block() } }

fun LifecycleAware.blockingAfterEach(block: suspend () -> Unit) =
    afterEachTest { runBlocking { block() } }
