package com.sedsoftware.screens.market.view

import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sedsoftware.screens.market.databinding.FragmentMarketScreenBinding
import com.sedsoftware.screens.market.view.MarketListView.ViewEvent
import com.sedsoftware.screens.market.view.MarketListView.ViewModel

class MarketListViewImpl(
    viewBinding: FragmentMarketScreenBinding
) : BaseMviView<ViewModel, ViewEvent>(), MarketListView {

    // Views
    private val marketFab: FloatingActionButton = viewBinding.marketFab

    override val renderer: ViewRenderer<ViewModel> = diff {
        diff(get = ViewModel::isFabEnabled, set = marketFab::setEnabled)
    }
}
