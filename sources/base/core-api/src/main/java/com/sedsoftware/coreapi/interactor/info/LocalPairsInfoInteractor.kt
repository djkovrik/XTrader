package com.sedsoftware.coreapi.interactor.info

import com.sedsoftware.coreapi.entity.misc.ExchangePairsState
import com.sedsoftware.coreapi.interactor.Interactor
import com.sedsoftware.coreutils.common.Either
import com.sedsoftware.coreutils.common.Failure

interface LocalPairsInfoInteractor : Interactor {

    fun checkIfPairsDownloaded(): Either<Failure, ExchangePairsState>
}
