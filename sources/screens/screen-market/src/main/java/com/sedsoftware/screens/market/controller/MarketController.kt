package com.sedsoftware.screens.market.controller

import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.core.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.lifecycle.doOnResumePause
import com.arkivanov.mvikotlin.core.view.MviView
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.sedsoftware.core.domain.errorhandler.CanShowError
import com.sedsoftware.core.domain.errorhandler.ErrorHandler
import com.sedsoftware.core.presentation.extension.mapNotNull
import com.sedsoftware.screens.market.MarketEvent
import com.sedsoftware.screens.market.store.MarketStore
import com.sedsoftware.screens.market.store.MarketStore.Intent
import com.sedsoftware.screens.market.view.MarketView.ViewEvent
import com.sedsoftware.screens.market.view.MarketView.ViewModel
import javax.inject.Inject

class MarketController @Inject constructor(
    private val store: MarketStore,
    private val errorHandler: ErrorHandler
) {

    fun onViewCreated(view: MviView<ViewModel, ViewEvent>, lifecycle: Lifecycle, errorHandlerView: CanShowError) {
        bind(lifecycle, BinderLifecycleMode.START_STOP) {
            store.states.mapNotNull(Mappers.stateToViewModel) bindTo view
        }

        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY) {
            view.events.mapNotNull(Mappers.viewEventToIntent) bindTo store
            store.labels.mapNotNull(Mappers.labelToEvent) bindTo { consumeEvent(it) }
        }

        lifecycle.doOnResumePause(
            onResume = { errorHandler.attachView(errorHandlerView) },
            onPause = { errorHandler.detachView() }
        )

        lifecycle.doOnDestroy(store::dispose)
    }

    fun onBackPressed() {
        if (store.state.isPairSelectionActive) {
            store.accept(Intent.ChangePairSelectionState(false))
        }
    }

    private fun consumeEvent(event: MarketEvent) {
        when (event) {
            is MarketEvent.HandleError -> errorHandler.consume(event.throwable)
        }
    }
}
