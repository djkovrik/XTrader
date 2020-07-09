package com.sedsoftware.screens.tickers.view

import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sedsoftware.screens.tickers.databinding.FragmentTickersScreenBinding
import com.sedsoftware.screens.tickers.view.TickersListView.ViewEvent
import com.sedsoftware.screens.tickers.view.TickersListView.ViewModel

class TickersListViewImpl(
    viewBinding: FragmentTickersScreenBinding
) : BaseMviView<ViewModel, ViewEvent>(), TickersListView {

    // Views
    private val tickersFab: FloatingActionButton = viewBinding.tickersFab

    override val renderer: ViewRenderer<ViewModel> = diff {
        diff(get = ViewModel::isFabEnabled, set = tickersFab::setEnabled)
    }
}
