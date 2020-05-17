package com.sedsoftware.screens.intro.exchanges.controller

import com.sedsoftware.core.domain.errorhandler.ErrorHandler
import com.sedsoftware.core.presentation.base.BaseController
import com.sedsoftware.screens.intro.exchanges.IntroExchangesEvent
import com.sedsoftware.screens.intro.exchanges.store.IntroExchangesStore
import com.sedsoftware.screens.intro.exchanges.store.IntroExchangesStore.Intent
import com.sedsoftware.screens.intro.exchanges.store.IntroExchangesStore.Label
import com.sedsoftware.screens.intro.exchanges.store.IntroExchangesStore.State
import com.sedsoftware.screens.intro.exchanges.view.IntroExchangesView.ViewEvent
import com.sedsoftware.screens.intro.exchanges.view.IntroExchangesView.ViewModel
import kotlinx.coroutines.channels.BroadcastChannel
import javax.inject.Inject

class IntroExchangesController @Inject constructor(
    override val store: IntroExchangesStore,
    override val eventBus: BroadcastChannel<IntroExchangesEvent>,
    override val errorHandler: ErrorHandler
) : BaseController<Intent, State, Label, ViewModel, IntroExchangesEvent, ViewEvent> {

    override val stateToViewModel: (State) -> ViewModel = { it.toViewModel() }
    override val viewEventToIntent: (ViewEvent) -> Intent = { it.toIntent() }
    override val labelToEvent: (Label) -> IntroExchangesEvent = { it.toEvent() }

    override fun consumeFeatureEvent(event: IntroExchangesEvent) {
        when (event) {
            is IntroExchangesEvent.HandleError -> errorHandler.consume(event.throwable)
        }
    }
}
