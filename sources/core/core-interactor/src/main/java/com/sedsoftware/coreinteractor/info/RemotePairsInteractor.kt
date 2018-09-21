package com.sedsoftware.coreinteractor.info

import com.sedsoftware.coreinteractor.Interactor
import com.sedsoftware.coreutils.common.Either
import com.sedsoftware.coreutils.common.Failure
import com.sedsoftware.coreutils.common.Success

interface RemotePairsInteractor : Interactor {

    fun downloadCurrencyPairs(): Either<Failure, Success>
}
