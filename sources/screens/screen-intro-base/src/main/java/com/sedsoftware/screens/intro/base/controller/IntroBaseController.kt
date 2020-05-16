package com.sedsoftware.screens.intro.base.controller

import com.sedsoftware.core.domain.errorhandler.ErrorHandler
import com.sedsoftware.core.presentation.base.BaseController
import com.sedsoftware.core.presentation.event.OneTimeEvent
import com.sedsoftware.screens.intro.base.store.IntroBaseStore
import com.sedsoftware.screens.intro.base.store.IntroBaseStore.Intent
import com.sedsoftware.screens.intro.base.store.IntroBaseStore.Label
import com.sedsoftware.screens.intro.base.store.IntroBaseStore.State
import com.sedsoftware.screens.intro.base.view.IntroBaseView.ViewEvent
import com.sedsoftware.screens.intro.base.view.IntroBaseView.ViewModel
import kotlinx.coroutines.channels.BroadcastChannel
import javax.inject.Inject

class IntroBaseController @Inject constructor(
    override val store: IntroBaseStore,
    override val eventBus: BroadcastChannel<OneTimeEvent>,
    override val errorHandler: ErrorHandler
) : BaseController<Intent, State, Label, ViewModel, ViewEvent> {

    override val stateToViewModel: (State) -> ViewModel = { it.toViewModel() }
    override val viewEventToIntent: (ViewEvent) -> Intent = { it.toIntent() }
    override val labelToEvent: (Label) -> OneTimeEvent = { it.toEvent() }
}
