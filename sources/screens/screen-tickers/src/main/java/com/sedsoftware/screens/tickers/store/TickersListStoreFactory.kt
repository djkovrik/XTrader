package com.sedsoftware.screens.tickers.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.sedsoftware.core.domain.entity.CurrencyPair
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.domain.interactor.PairTicksManager
import com.sedsoftware.screens.tickers.store.TickersListStore.Intent
import com.sedsoftware.screens.tickers.store.TickersListStore.Label
import com.sedsoftware.screens.tickers.store.TickersListStore.Result
import com.sedsoftware.screens.tickers.store.TickersListStore.State
import com.sedsoftware.screens.tickers.store.PairSelectionStore.Action
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.merge

class TickersListStoreFactory(
    private val storeFactory: StoreFactory,
    private val managers: Map<Exchange, @JvmSuppressWildcards PairTicksManager>
) {

    fun create(): TickersListStore =
        object : TickersListStore, Store<Intent, State, Label> by storeFactory.create(
            name = "TickersListStore",
            initialState = State(),
            executorFactory = ::TickersListExecutor,
            reducer = TickersListReducer
        ) {}

    private object TickersListReducer : Reducer<State, Result> {
        override fun State.reduce(result: Result): State =
            when (result) {
                is Result.TicksRefreshed -> copy(ticks = result.list)
                is Result.SelectorAvailable -> copy(pairSelectorAvailable = true)
            }
    }

    private inner class TickersListExecutor : SuspendExecutor<Intent, Action, State, Result, Label>() {
        override suspend fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.EnablePairSelector -> dispatch(Result.SelectorAvailable)
                is Intent.AddCurrencyPair -> addCurrencyPairToWatchList(intent.pair)
            }
        }

        private suspend fun addCurrencyPairToWatchList(pair: CurrencyPair) {
            try {
                managers[pair.exchange]?.let { manager ->
                    manager.addPairToWatchList(pair)
                    publish(Label.WatchListRefreshed)
                }
            } catch (throwable: Throwable) {
                publish(Label.ErrorCaught(throwable))
            }
        }

        private suspend fun refreshWatchList() {
            try {
                managers
                    .filterValues { it.hasTicks() }
                    .forEach { (_, manager) -> manager.refreshTicks() }
            } catch (throwable: Throwable) {
                publish(Label.ErrorCaught(throwable))
            }
        }

        private suspend fun rebuildWatchList() {
            try {
                managers
                    .filterValues { it.hasTicks() }
                    .map { (_, manager) -> manager.watchForTicks() }
                    .toList()
                    .merge()
                    .collect { dispatch(Result.TicksRefreshed(it)) }
            } catch (throwable: Throwable) {
                publish(Label.ErrorCaught(throwable))
            }
        }
    }
}
