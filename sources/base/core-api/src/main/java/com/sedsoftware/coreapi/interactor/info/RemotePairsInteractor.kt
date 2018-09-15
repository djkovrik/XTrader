package com.sedsoftware.coreapi.interactor.info

import com.sedsoftware.coreapi.interactor.Interactor
import com.sedsoftware.coreutils.common.Either
import com.sedsoftware.coreutils.common.Failure
import com.sedsoftware.coreutils.common.Success

interface RemotePairsInteractor : Interactor {

    fun downloadCurrencyPairs(): Either<Failure, Success>
}
