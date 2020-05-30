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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull as mapNotNullFlow

interface BaseController<Intent : Any, State : Any, Label : Any, ViewModel : Any, FeatureEvent : Any, ViewEvent : Any> {

    val store: Store<Intent, State, Label>
    val errorHandler: ErrorHandler

    val stateToViewModel: State.() -> ViewModel
    val viewEventToIntent: ViewEvent.() -> Intent
    val labelToEvent: Label.() -> FeatureEvent

    val eventConsumer: (FeatureEvent) -> Unit

    fun onViewCreated(view: MviView<ViewModel, ViewEvent>, lifecycle: Lifecycle, errorHandlerView: CanShowError) {
        bind(lifecycle, BinderLifecycleMode.START_STOP) {
            store.states.mapNotNull(stateToViewModel) bindTo view
        }

        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY) {
            view.events.mapNotNull(viewEventToIntent) bindTo store
            store.labels.mapNotNull(labelToEvent) bindTo { eventConsumer(it) }
        }

        lifecycle.doOnResumePause(
            onResume = { errorHandler.attachView(errorHandlerView) },
            onPause = { errorHandler.detachView() }
        )

        lifecycle.doOnDestroy(store::dispose)
    }

    private inline fun <T, R : Any> Flow<T>.mapNotNull(crossinline mapper: (T) -> R?): Flow<R> =
        mapNotNullFlow { mapper(it) }
}
