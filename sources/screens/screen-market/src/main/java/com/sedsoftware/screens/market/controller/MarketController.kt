package com.sedsoftware.screens.market.controller

import com.sedsoftware.core.domain.errorhandler.ErrorHandler
import com.sedsoftware.core.presentation.base.BaseController
import com.sedsoftware.core.presentation.event.OneTimeEvent
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
    override val eventBus: BroadcastChannel<OneTimeEvent>,
    override val errorHandler: ErrorHandler
) : BaseController<Intent, State, Label, ViewModel, ViewEvent> {

    override val stateToViewModel: (State) -> ViewModel = { it.toViewModel() }
    override val viewEventToIntent: (ViewEvent) -> Intent = { it.toIntent() }
    override val labelToEvent: (Label) -> OneTimeEvent = { it.toEvent() }
}
