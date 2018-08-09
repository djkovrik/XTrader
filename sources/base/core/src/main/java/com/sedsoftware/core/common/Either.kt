package com.sedsoftware.core.common

sealed class Either<out L, out R> {
    data class Error<out L>(val a: L) : Either<L, Nothing>()
    data class Success<out R>(val b: R) : Either<Nothing, R>()

    val isSuccess get() = this is Success<R>
    val isError get() = this is Error<L>

    fun <L> error(a: L) = Either.Error(a)
    fun <R> success(b: R) = Either.Success(b)

    fun either(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Error -> fnL(a)
            is Success -> fnR(b)
        }
}

fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
    f(this(it))
}

fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> =
    when (this) {
        is Either.Error -> Either.Error(a)
        is Either.Success -> fn(b)
    }

fun <T, L, R> Either<L, R>.map(fn: (R) -> (T)): Either<L, T> = this.flatMap(fn.c(::success))
