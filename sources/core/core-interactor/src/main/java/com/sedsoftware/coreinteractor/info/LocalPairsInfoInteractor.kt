package com.sedsoftware.coreinteractor.info

import com.sedsoftware.coreentity.misc.ExchangePairsState
import com.sedsoftware.coreinteractor.Interactor
import com.sedsoftware.coreutils.common.Either
import com.sedsoftware.coreutils.common.Failure

interface LocalPairsInfoInteractor : Interactor {

    fun checkIfPairsDownloaded(): Either<Failure, ExchangePairsState>
}
