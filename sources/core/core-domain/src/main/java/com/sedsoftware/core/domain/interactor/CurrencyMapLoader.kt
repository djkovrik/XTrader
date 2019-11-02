package com.sedsoftware.core.domain.interactor

import com.sedsoftware.core.utils.type.Either
import com.sedsoftware.core.utils.type.Failure
import com.sedsoftware.core.utils.type.Success

interface CurrencyMapLoader {
    suspend fun isLoadingNeeded(): Either<Failure, Boolean>
    suspend fun loadCurrencyMap(): Either<Failure, Success>
}
