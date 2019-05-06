package com.sedsoftware.core.utils.network

import com.sedsoftware.core.utils.common.Either
import com.sedsoftware.core.utils.common.Either.Left
import com.sedsoftware.core.utils.common.Either.Right
import com.sedsoftware.core.utils.common.Failure
import com.sedsoftware.core.utils.common.Failure.ServerError

suspend fun <T> safeApiCall(request: suspend () -> T): Either<Failure, T> {
    return try {
        val response = request.invoke()
        Right(response)
    } catch (exception: Throwable) {
        Left(ServerError(exception))
    }
}
