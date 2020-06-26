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
import com.sedsoftware.screens.market.controller.mappers.CommonMappers
import com.sedsoftware.screens.market.controller.mappers.MarketListMappers
import com.sedsoftware.screens.market.controller.mappers.PairSelectionMappers
import com.sedsoftware.screens.market.store.MarketListStore
import com.sedsoftware.screens.market.store.PairSelectionStore
import com.sedsoftware.screens.market.store.PairSelectionStore.Intent
import javax.inject.Inject
import com.sedsoftware.screens.market.view.MarketListView.ViewEvent as MarketViewEvent
import com.sedsoftware.screens.market.view.MarketListView.ViewModel as MarketViewModel
import com.sedsoftware.screens.market.view.PairSelectionView.ViewEvent as SelectorViewEvent
import com.sedsoftware.screens.market.view.PairSelectionView.ViewModel as SelectorViewModel

class MarketController @Inject constructor(
    private val pairSelectionStore: PairSelectionStore,
    private val marketListStore: MarketListStore,
    private val errorHandler: ErrorHandler
) {

    fun onViewCreated(
        selectorView: MviView<SelectorViewModel, SelectorViewEvent>,
        marketView: MviView<MarketViewModel, MarketViewEvent>,
        lifecycle: Lifecycle,
        errorHandlerView: CanShowError
    ) {

        bind(lifecycle, BinderLifecycleMode.START_STOP) {
            pairSelectionStore.states.mapNotNull(PairSelectionMappers.stateToViewModel) bindTo selectorView
            marketListStore.states.mapNotNull(MarketListMappers.stateToViewModel) bindTo marketView
        }

        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY) {
            selectorView.events.mapNotNull(PairSelectionMappers.viewEventToIntent) bindTo pairSelectionStore
            pairSelectionStore.labels.mapNotNull(CommonMappers.selectorLabelToIntent) bindTo marketListStore
            pairSelectionStore.labels.mapNotNull(PairSelectionMappers.labelToEvent) bindTo { consumeEvent(it) }
        }

        lifecycle.doOnResumePause(
            onResume = { errorHandler.attachView(errorHandlerView) },
            onPause = { errorHandler.detachView() }
        )

        lifecycle.doOnDestroy {
            pairSelectionStore.dispose()
            marketListStore.dispose()
        }
    }

    fun onBackPressed() {
        if (pairSelectionStore.state.isPairSelectionActive) {
            pairSelectionStore.accept(Intent.ChangePairSelectionState(false))
        }
    }

    private fun consumeEvent(event: MarketEvent) {
        when (event) {
            is MarketEvent.HandleError -> errorHandler.consume(event.throwable)
        }
    }
}
