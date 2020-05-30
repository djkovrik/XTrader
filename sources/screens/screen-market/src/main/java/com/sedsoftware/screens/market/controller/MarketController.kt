package com.sedsoftware.screens.market.controller

import com.sedsoftware.core.domain.errorhandler.ErrorHandler
import com.sedsoftware.core.presentation.base.BaseController
import com.sedsoftware.screens.market.MarketEvent
import com.sedsoftware.screens.market.store.MarketStore
import com.sedsoftware.screens.market.store.MarketStore.Intent
import com.sedsoftware.screens.market.store.MarketStore.Label
import com.sedsoftware.screens.market.store.MarketStore.State
import com.sedsoftware.screens.market.view.MarketView.ViewEvent
import com.sedsoftware.screens.market.view.MarketView.ViewModel
import javax.inject.Inject

class MarketController @Inject constructor(
    override val store: MarketStore,
    override val errorHandler: ErrorHandler
) : BaseController<Intent, State, Label, ViewModel, MarketEvent, ViewEvent> {

    override val stateToViewModel: State.() -> ViewModel = Mappers.stateToViewModel
    override val viewEventToIntent: ViewEvent.() -> Intent = Mappers.viewEventToIntent
    override val labelToEvent: Label.() -> MarketEvent = Mappers.labelToEvent

    override val eventConsumer: (MarketEvent) -> Unit = { event ->
        when (event) {
            is MarketEvent.HandleError -> errorHandler.consume(event.throwable)
        }
    }

    fun onBackPressed() {
        if (store.state.isPairSelectionActive) {
            store.accept(Intent.ChangePairSelectionState(false))
        }
    }
}
