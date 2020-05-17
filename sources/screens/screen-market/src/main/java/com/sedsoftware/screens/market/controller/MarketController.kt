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
import kotlinx.coroutines.channels.BroadcastChannel
import javax.inject.Inject

class MarketController @Inject constructor(
    override val store: MarketStore,
    override val eventBus: BroadcastChannel<MarketEvent>,
    override val errorHandler: ErrorHandler
) : BaseController<Intent, State, Label, ViewModel, MarketEvent, ViewEvent> {

    override val stateToViewModel: (State) -> ViewModel = { it.toViewModel() }
    override val viewEventToIntent: (ViewEvent) -> Intent = { it.toIntent() }
    override val labelToEvent: (Label) -> MarketEvent = { it.toEvent() }

    override fun consumeFeatureEvent(event: MarketEvent) {
        when (event) {
            is MarketEvent.HandleError -> errorHandler.consume(event.throwable)
        }
    }
}
