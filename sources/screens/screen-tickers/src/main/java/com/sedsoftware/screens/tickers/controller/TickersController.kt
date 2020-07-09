package com.sedsoftware.screens.tickers.controller

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
import com.sedsoftware.screens.tickers.TickersEvent
import com.sedsoftware.screens.tickers.controller.mappers.CommonMappers.selectorLabelToTickersIntent
import com.sedsoftware.screens.tickers.controller.mappers.TickersListMappers.tickersStateToViewModel
import com.sedsoftware.screens.tickers.controller.mappers.PairSelectionMappers.selectorLabelToEvent
import com.sedsoftware.screens.tickers.controller.mappers.PairSelectionMappers.selectorStateToViewModel
import com.sedsoftware.screens.tickers.controller.mappers.PairSelectionMappers.selectorViewEventToIntent
import com.sedsoftware.screens.tickers.store.TickersListStore
import com.sedsoftware.screens.tickers.store.PairSelectionStore
import javax.inject.Inject
import com.sedsoftware.screens.tickers.view.TickersListView.ViewEvent as TickersViewEvent
import com.sedsoftware.screens.tickers.view.TickersListView.ViewModel as TickersViewModel
import com.sedsoftware.screens.tickers.view.PairSelectionView.ViewEvent as SelectorViewEvent
import com.sedsoftware.screens.tickers.view.PairSelectionView.ViewModel as SelectorViewModel

class TickersController @Inject constructor(
    private val pairSelectionStore: PairSelectionStore,
    private val tickersListStore: TickersListStore,
    private val errorHandler: ErrorHandler
) {

    fun onViewCreated(
        selectorView: MviView<SelectorViewModel, SelectorViewEvent>,
        tickersView: MviView<TickersViewModel, TickersViewEvent>,
        lifecycle: Lifecycle,
        errorHandlerView: CanShowError
    ) {

        bind(lifecycle, BinderLifecycleMode.START_STOP) {
            pairSelectionStore.states.mapNotNull(selectorStateToViewModel) bindTo selectorView
            tickersListStore.states.mapNotNull(tickersStateToViewModel) bindTo tickersView
        }

        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY) {
            pairSelectionStore.labels.mapNotNull(selectorLabelToEvent) bindTo { consumeEvent(it) }
            pairSelectionStore.labels.mapNotNull(selectorLabelToTickersIntent) bindTo tickersListStore
            selectorView.events.mapNotNull(selectorViewEventToIntent) bindTo pairSelectionStore
        }

        lifecycle.doOnResumePause(
            onResume = { errorHandler.attachView(errorHandlerView) },
            onPause = { errorHandler.detachView() }
        )

        lifecycle.doOnDestroy {
            pairSelectionStore.dispose()
            tickersListStore.dispose()
        }
    }

    private fun consumeEvent(event: TickersEvent) {
        when (event) {
            is TickersEvent.HandleError -> errorHandler.consume(event.throwable)
        }
    }
}
