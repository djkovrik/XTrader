package com.sedsoftware.screens.market.controller

import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.core.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.lifecycle.doOnResumePause
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.sedsoftware.core.domain.errorhandler.CanShowError
import com.sedsoftware.core.domain.errorhandler.ErrorHandler
import com.sedsoftware.core.presentation.event.OneTimeEvent
import com.sedsoftware.screens.market.store.MarketStore
import com.sedsoftware.screens.market.view.MarketView
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MarketController @Inject constructor(
    private val store: MarketStore,
    private val eventBus: BroadcastChannel<OneTimeEvent>,
    private val errorHandler: ErrorHandler
) {

    fun onViewCreated(view: MarketView, lifecycle: Lifecycle, errorHandlerView: CanShowError) {
        bind(lifecycle, BinderLifecycleMode.START_STOP) {
            store.states.map { it.toViewModel() } bindTo view
        }

        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY) {
            view.events.map { it.toIntent() } bindTo store
            store.labels.map { it.toEvent() } bindTo { eventBus.send(it) }
            eventBus.asFlow().filterNotNull() bindTo { consumeEvent(it) }
        }

        lifecycle.doOnResumePause(
            onResume = { errorHandler.attachView(errorHandlerView) },
            onPause = { errorHandler.detachView() }
        )

        lifecycle.doOnDestroy {
            store.dispose()
        }
    }

    private fun consumeEvent(event: OneTimeEvent) {
        when (event) {
            is OneTimeEvent.HandleError -> errorHandler.consume(event.throwable)
        }
    }
}
