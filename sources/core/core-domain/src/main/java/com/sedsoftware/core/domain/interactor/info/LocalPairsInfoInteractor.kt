package com.sedsoftware.core.domain.interactor.info

import com.sedsoftware.core.domain.entity.misc.ExchangePairsState
import com.sedsoftware.core.domain.interactor.Interactor
import com.sedsoftware.core.utils.common.Either
import com.sedsoftware.core.utils.common.Failure

interface LocalPairsInfoInteractor : Interactor {
    fun checkIfPairsDownloaded(): Either<Failure, ExchangePairsState>
}
