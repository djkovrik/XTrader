package com.sedsoftware.screens.market.controller

import com.sedsoftware.core.domain.errorhandler.ErrorHandler
import com.sedsoftware.screens.market.MarketEvent
import com.sedsoftware.screens.market.store.PairSelectionStore
import com.sedsoftware.screens.market.store.PairSelectionStore.Intent
import javax.inject.Inject

class MarketController @Inject constructor(
    private val pairSelectionStore: PairSelectionStore,
    private val errorHandler: ErrorHandler
) {

    fun onBackPressed() {
        if (pairSelectionStore.state.isPairSelectionActive) {
            pairSelectionStore.accept(Intent.ChangePairSelectionState(false))
        }
    }

    private fun consumeEvent(event: MarketEvent) {
        when (event) {
            is MarketEvent.HandleError -> errorHandler.consume(event.throwable)
        }
    }
}
