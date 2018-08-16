package com.sedsoftware.core.interactor.info

import com.sedsoftware.core.common.Either
import com.sedsoftware.core.common.Failure
import com.sedsoftware.core.common.Success
import com.sedsoftware.core.interactor.Interactor

interface RemotePairsInteractor : Interactor {

    fun downloadCurrencyPairs(): Either<Failure, Success>
}
