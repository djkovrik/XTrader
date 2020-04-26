package com.sedsoftware.screens.intro.base.controller

import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.core.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.sedsoftware.core.domain.errorhandler.CanShowError
import com.sedsoftware.core.domain.errorhandler.ErrorHandler
import com.sedsoftware.screens.intro.base.OneTimeEvent
import com.sedsoftware.screens.intro.base.store.IntroBaseStore
import com.sedsoftware.screens.intro.base.view.IntroBaseView
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// TODO ExperimentalCoroutinesApi !
class IntroBaseController @Inject constructor(
    private val introBaseStore: IntroBaseStore,
    private val eventBus: BroadcastChannel<OneTimeEvent>,
    private val errorHandler: ErrorHandler
) {

    fun onViewCreated(introBaseView: IntroBaseView, viewLifecycle: Lifecycle, errorHandlerView: CanShowError) {
        errorHandler.attachView(errorHandlerView)

        bind(viewLifecycle, BinderLifecycleMode.START_STOP) {
            introBaseStore.states.map { it.toViewModel() } bindTo introBaseView
        }

        bind(viewLifecycle, BinderLifecycleMode.CREATE_DESTROY) {
            introBaseView.events.map { it.toIntent() } bindTo introBaseStore
            introBaseStore.labels.map { it.toEvent() } bindTo { eventBus.send(it) }
            eventBus.asFlow().filterNotNull() bindTo { consumeEvent(it) }
        }

        viewLifecycle.doOnDestroy {
            introBaseStore.dispose()
            errorHandler.detachView()
        }
    }

    private fun consumeEvent(event: OneTimeEvent) {
        when (event) {
            is OneTimeEvent.HandleError -> errorHandler.consume(event.throwable)
        }
    }
}
