package com.sedsoftware.domain.interactor

import com.sedsoftware.domain.common.ErrorCallback
import com.sedsoftware.domain.common.SuccessCallback
import com.sedsoftware.domain.entity.CurrencyPair
import com.sedsoftware.domain.exception.EmptyServerResponse
import com.sedsoftware.domain.executor.Executor
import com.sedsoftware.domain.repository.CurrencyPairsRepository
import kotlinx.coroutines.experimental.channels.consumeEach
import javax.inject.Inject

class GetCurrencyPairsUseCase @Inject constructor(
  private val repository: CurrencyPairsRepository,
  executor: Executor
) : UseCase(executor) {

  fun getPairs(onSuccess: SuccessCallback<List<CurrencyPair>>, onError: ErrorCallback) =
    postExecute {
      repository.getCurrencyPairsList().consumeEach { pairsList ->
        when {
          pairsList.isNotEmpty() ->
            onSuccess(pairsList)
          else ->
            onError(EmptyServerResponse("Pairs list is empty"))
        }
      }
    }
}
