package com.sedsoftware.core.presentation.base

import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.core.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.lifecycle.doOnResumePause
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.view.MviView
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.sedsoftware.core.domain.errorhandler.CanShowError
import com.sedsoftware.core.domain.errorhandler.ErrorHandler
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

interface BaseController<Intent : Any, State : Any, Label : Any, ViewModel : Any, FeatureEvent : Any, ViewEvent : Any> {

    val store: Store<Intent, State, Label>
    val eventBus: BroadcastChannel<FeatureEvent>
    val errorHandler: ErrorHandler

    val stateToViewModel: (State) -> ViewModel
    val viewEventToIntent: (ViewEvent) -> Intent
    val labelToEvent: (Label) -> FeatureEvent

    fun onViewCreated(view: MviView<ViewModel, ViewEvent>, lifecycle: Lifecycle, errorHandlerView: CanShowError) {
        bind(lifecycle, BinderLifecycleMode.START_STOP) {
            store.states.map { stateToViewModel(it) } bindTo view
        }

        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY) {
            view.events.map { viewEventToIntent(it) } bindTo store
            store.labels.map { labelToEvent(it) } bindTo { eventBus.send(it) }
            eventBus.asFlow().filterNotNull() bindTo { consumeFeatureEvent(it) }
        }

        lifecycle.doOnResumePause(
            onResume = { errorHandler.attachView(errorHandlerView) },
            onPause = { errorHandler.detachView() }
        )

        lifecycle.doOnDestroy {
            store.dispose()
        }
    }

    fun consumeFeatureEvent(event: FeatureEvent)
}
