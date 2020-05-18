package com.sedsoftware.screens.market.view

import android.content.Context
import android.graphics.Point
import android.view.Display
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sedsoftware.core.presentation.extension.centerX
import com.sedsoftware.core.presentation.extension.centerY
import com.sedsoftware.screens.market.R
import com.sedsoftware.screens.market.databinding.FragmentMarketScreenBinding
import com.sedsoftware.screens.market.view.MarketView.ViewEvent
import com.sedsoftware.screens.market.view.MarketView.ViewModel
import com.sedsoftware.screens.market.view.adapter.CurrencyListAdapter
import com.sedsoftware.screens.market.view.model.CurrencyListItem
import com.sedsoftware.screens.market.view.model.ExchangeListItem

class MarketViewImpl(
    private val context: Context,
    private val display: Display,
    viewBinding: FragmentMarketScreenBinding
) : BaseMviView<ViewModel, ViewEvent>(), MarketView {

    // Views
    private val marketFab: FloatingActionButton = viewBinding.marketFab
    private val globalOverlayView: View = viewBinding.globalOverlayView
    private val overlayView: View = viewBinding.includedLayout.overlayView
    private val overlayImageView: ImageView = viewBinding.includedLayout.overlayImageView
    private val addPairPanel: View = viewBinding.includedLayout.includedLayout
    private val exchangeTextView: TextView = viewBinding.includedLayout.exchangeTextView
    private val baseRecyclerView: RecyclerView = viewBinding.includedLayout.baseRecyclerView
    private val marketRecyclerView: RecyclerView = viewBinding.includedLayout.marketRecyclerView
    private val baseCurrencyTextView: TextView = viewBinding.includedLayout.baseCurrencyTextView
    private val baseFullCurrencyTextView: TextView = viewBinding.includedLayout.baseFullCurrencyTextView
    private val marketCurrencyTextView: TextView = viewBinding.includedLayout.marketCurrencyTextView
    private val marketFullCurrencyTextView: TextView = viewBinding.includedLayout.marketFullCurrencyTextView

    // Motion params
    private var defaultDialogCenterX = 0f
    private var defaultDialogCenterY = 0f
    private var dialogTranslationX = 0f
    private var dialogTranslationY = 0f
    private var defaultFabCenterX = 0f
    private var defaultFabCenterY = 0f
    private var isPairSelectionExpanded = false

    private val baseAdapter: CurrencyListAdapter = CurrencyListAdapter(
        clickListener = object : CurrencyListAdapter.Listener {
            override fun onItemClick(item: CurrencyListItem) {
                dispatch(ViewEvent.BaseCurrencySelected(item.currency))
            }
        }
    )

    private val marketAdapter: CurrencyListAdapter = CurrencyListAdapter(
        clickListener = object : CurrencyListAdapter.Listener {
            override fun onItemClick(item: CurrencyListItem) {
                dispatch(ViewEvent.MarketCurrencySelected(item.currency))
            }
        }
    )

    init {
        addPairPanel.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                setupBaseMotionParams()
                setupAddPairViewState()
                addPairPanel.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

//        exchangeTextView.setOnClickListener { dispatch(ViewEvent.ShowExchangeList) }
        overlayView.setOnClickListener { dispatch(ViewEvent.ShowPairSelectionView(false)) }
        marketFab.setOnClickListener { dispatch(ViewEvent.ShowPairSelectionView(true)) }

        overlayView.setOnTouchListener { _, event ->
            var flag = false
            if (event.action == MotionEvent.ACTION_DOWN) {
                flag = overlayView.measuredHeight - event.y < addPairPanel.measuredHeight
            }
            flag
        }

        addPairPanel.layoutParams.height = calculatePanelHeight()

        baseRecyclerView.adapter = baseAdapter
        marketRecyclerView.adapter = marketAdapter

        baseRecyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL).apply {
            ContextCompat.getDrawable(context, R.drawable.divider)?.let { setDrawable(it) }
        })

        marketRecyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL).apply {
            ContextCompat.getDrawable(context, R.drawable.divider)?.let { setDrawable(it) }
        })
    }

    override val renderer: ViewRenderer<ViewModel> = diff {
        diff(get = ViewModel::exchanges, compare = { a, b -> a === b }, set = ::showSelectedExchange)
        diff(get = ViewModel::baseCurrencies, compare = { a, b -> a === b }, set = ::showBaseCurrencies)
        diff(get = ViewModel::marketCurrencies, compare = { a, b -> a === b }, set = ::showMarketCurrencies)
        diff(get = ViewModel::isFabAvailable, set = marketFab::setEnabled)
        diff(get = ViewModel::isPairSelectionViewActive, set = ::showPairSelectionView)
    }

    private fun showSelectedExchange(exchanges: List<ExchangeListItem>) {
        exchanges.find { it.isSelected }?.let { exchangeTextView.text = it.exchange.label }
    }

    private fun showBaseCurrencies(currencies: List<CurrencyListItem>) {
        baseAdapter.items = currencies
        baseAdapter.notifyDataSetChanged()

        currencies.find { it.isSelected }?.let { selected ->
            baseCurrencyTextView.text = selected.currency.name
            baseFullCurrencyTextView.text = selected.currency.label
        }
    }

    private fun showMarketCurrencies(currencies: List<CurrencyListItem>) {
        marketAdapter.items = currencies
        marketAdapter.notifyDataSetChanged()

        currencies.find { it.isSelected }?.let { selected ->
            marketCurrencyTextView.text = selected.currency.name
            marketFullCurrencyTextView.text = selected.currency.label
        }
    }

    private fun showPairSelectionView(show: Boolean) {
        isPairSelectionExpanded = show
        setupAddPairViewState()
    }

    // 3/4 of the screen height
    @Suppress("MagicNumber")
    private fun calculatePanelHeight(): Int {
        val size = Point()
        display.getSize(size)
        return size.y / 4 * 3
    }

    private fun setupBaseMotionParams() {
        defaultDialogCenterX = addPairPanel.centerX()
        defaultDialogCenterY = addPairPanel.centerY()
        defaultFabCenterX = marketFab.centerX()
        defaultFabCenterY = marketFab.centerY()

        dialogTranslationX = defaultFabCenterX - defaultDialogCenterX
        dialogTranslationY = defaultFabCenterY - defaultDialogCenterY
    }

    private fun setupAddPairViewState() {
        if (isPairSelectionExpanded) {
            addPairPanel.translationX = 0f
            addPairPanel.translationY = 0f

            addPairPanel.isVisible = true
            overlayView.isVisible = true

            marketFab.isGone = true
            overlayView.isGone = true
            overlayImageView.isGone = true
        } else {
            addPairPanel.translationX = dialogTranslationX
            addPairPanel.translationY = dialogTranslationY

            addPairPanel.isGone = true
            overlayView.isGone = true

            marketFab.isVisible = true
            overlayView.isVisible = true
            overlayImageView.isVisible = true
        }
    }
}
