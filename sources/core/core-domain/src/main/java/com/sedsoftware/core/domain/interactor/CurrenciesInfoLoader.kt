package com.sedsoftware.core.domain.interactor

import com.sedsoftware.core.utils.type.Either
import com.sedsoftware.core.utils.type.Failure
import com.sedsoftware.core.utils.type.Success

interface CurrenciesInfoLoader {
    suspend fun loadInfoIfNeeded(): Either<Failure, Success>
}
