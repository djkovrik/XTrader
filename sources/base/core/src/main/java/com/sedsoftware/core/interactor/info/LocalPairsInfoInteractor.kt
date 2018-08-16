package com.sedsoftware.core.interactor.info

import com.sedsoftware.core.common.Either
import com.sedsoftware.core.common.Failure
import com.sedsoftware.core.entity.ExchangePairsState
import com.sedsoftware.core.interactor.Interactor

interface LocalPairsInfoInteractor : Interactor {

    fun checkIfPairsDownloaded(): Either<Failure, ExchangePairsState>
}
