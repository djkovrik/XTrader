package com.sedsoftware.core.utils.network

import com.sedsoftware.core.utils.type.Either
import com.sedsoftware.core.utils.type.Either.Left
import com.sedsoftware.core.utils.type.Either.Right
import com.sedsoftware.core.utils.type.Failure
import com.sedsoftware.core.utils.type.Failure.ServerError

suspend fun <T> safeApiCall(request: suspend () -> T): Either<Failure, T> {
    return try {
        val response = request.invoke()
        Right(response)
    } catch (exception: Throwable) {
        Left(ServerError(exception))
    }
}
