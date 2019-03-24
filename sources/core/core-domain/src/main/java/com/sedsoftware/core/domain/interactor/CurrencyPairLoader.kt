package com.sedsoftware.core.domain.interactor

import com.sedsoftware.core.utils.common.Either
import com.sedsoftware.core.utils.common.Failure
import com.sedsoftware.core.utils.common.Success

interface CurrencyPairLoader {
    fun fetchCurrencyPairs(): Either<Failure, Success>
}
