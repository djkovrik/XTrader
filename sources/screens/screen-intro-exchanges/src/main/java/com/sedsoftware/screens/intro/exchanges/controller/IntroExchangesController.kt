package com.sedsoftware.screens.intro.exchanges.controller

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
import com.sedsoftware.screens.intro.exchanges.IntroExchangesEvent
import com.sedsoftware.screens.intro.exchanges.controller.Mappers.labelToEvent
import com.sedsoftware.screens.intro.exchanges.controller.Mappers.stateToViewModel
import com.sedsoftware.screens.intro.exchanges.controller.Mappers.viewEventToIntent
import com.sedsoftware.screens.intro.exchanges.store.IntroExchangesStore
import com.sedsoftware.screens.intro.exchanges.view.IntroExchangesView.ViewEvent
import com.sedsoftware.screens.intro.exchanges.view.IntroExchangesView.ViewModel
import javax.inject.Inject

class IntroExchangesController @Inject constructor(
    private val store: IntroExchangesStore,
    private val errorHandler: ErrorHandler
) {

    fun onViewCreated(view: MviView<ViewModel, ViewEvent>, lifecycle: Lifecycle, errorHandlerView: CanShowError) {
        bind(lifecycle, BinderLifecycleMode.START_STOP) {
            store.states.mapNotNull(stateToViewModel) bindTo view
        }

        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY) {
            view.events.mapNotNull(viewEventToIntent) bindTo store
            store.labels.mapNotNull(labelToEvent) bindTo { consumeEvent(it) }
        }

        lifecycle.doOnResumePause(
            onResume = { errorHandler.attachView(errorHandlerView) },
            onPause = { errorHandler.detachView() }
        )

        lifecycle.doOnDestroy(store::dispose)
    }

    private fun consumeEvent(event: IntroExchangesEvent) {
        when (event) {
            is IntroExchangesEvent.HandleError -> errorHandler.consume(event.throwable)
        }
    }
}
