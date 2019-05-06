package com.sedsoftware.core.utils.extension

import com.sedsoftware.core.utils.common.Either
import com.sedsoftware.core.utils.common.Either.Left
import com.sedsoftware.core.utils.common.Either.Right

fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> =
    when (this) {
        is Left -> Left(a)
        is Right -> fn(b)
    }

fun <T, L, R> Either<L, R>.map(fn: (R) -> (T)): Either<L, T> = this.flatMap(fn.c(::right))

fun <T, L, R> Either<L, R>.either(fnL: (L) -> T, fnR: (R) -> T): T =
    when (this) {
        is Left -> fnL(a)
        is Right -> fnR(b)
    }

fun <L> left(a: L) = Left(a)

fun <R> right(b: R) = Right(b)

fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = { f(this(it)) }
