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
import com.sedsoftware.screens.market.controller.mappers.CommonMappers.selectorLabelToMarketIntent
import com.sedsoftware.screens.market.controller.mappers.MarketListMappers.marketStateToViewModel
import com.sedsoftware.screens.market.controller.mappers.PairSelectionMappers.selectorLabelToEvent
import com.sedsoftware.screens.market.controller.mappers.PairSelectionMappers.selectorStateToViewModel
import com.sedsoftware.screens.market.controller.mappers.PairSelectionMappers.selectorViewEventToIntent
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
            pairSelectionStore.states.mapNotNull(selectorStateToViewModel) bindTo selectorView
            marketListStore.states.mapNotNull(marketStateToViewModel) bindTo marketView
        }

        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY) {
            pairSelectionStore.labels.mapNotNull(selectorLabelToEvent) bindTo { consumeEvent(it) }
            pairSelectionStore.labels.mapNotNull(selectorLabelToMarketIntent) bindTo marketListStore
            selectorView.events.mapNotNull(selectorViewEventToIntent) bindTo pairSelectionStore
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
