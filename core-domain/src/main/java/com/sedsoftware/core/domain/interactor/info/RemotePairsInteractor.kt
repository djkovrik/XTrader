package com.sedsoftware.core.domain.interactor.info

import com.sedsoftware.core.domain.interactor.Interactor
import com.sedsoftware.core.utils.common.Either
import com.sedsoftware.core.utils.common.Failure
import com.sedsoftware.core.utils.common.Success

interface RemotePairsInteractor : Interactor {
    fun downloadCurrencyPairs(): Either<Failure, Success>
}
