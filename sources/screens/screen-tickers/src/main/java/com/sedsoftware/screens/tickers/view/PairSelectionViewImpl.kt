package com.sedsoftware.screens.tickers.view

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Point
import android.view.Display
import android.view.MotionEvent
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.ArcMotion
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sedsoftware.core.presentation.extension.addEndAction
import com.sedsoftware.core.presentation.extension.addStartEndActions
import com.sedsoftware.core.presentation.extension.centerX
import com.sedsoftware.core.presentation.extension.centerY
import com.sedsoftware.screens.tickers.R
import com.sedsoftware.screens.tickers.databinding.FragmentTickersScreenBinding
import com.sedsoftware.screens.tickers.ui.OnBackPressedInvoker
import com.sedsoftware.screens.tickers.ui.adapter.CurrencyListAdapter
import com.sedsoftware.screens.tickers.ui.model.CurrencyListItem
import com.sedsoftware.screens.tickers.ui.model.ExchangeListItem
import com.sedsoftware.screens.tickers.view.PairSelectionView.ViewEvent
import com.sedsoftware.screens.tickers.view.PairSelectionView.ViewModel

@SuppressLint("ClickableViewAccessibility")
class PairSelectionViewImpl(
    private val context: Context,
    private val onBackPressedCallback: OnBackPressedCallback,
    private val display: Display,
    onBackPressedInvoker: OnBackPressedInvoker,
    viewBinding: FragmentTickersScreenBinding
) : BaseMviView<ViewModel, ViewEvent>(), PairSelectionView {

    // Views
    private val tickersFab: FloatingActionButton = viewBinding.tickersFab
    private val addPairButton: MaterialButton = viewBinding.includedLayout.addPairButton
    private val overlayView: View = viewBinding.includedLayout.overlayView
    private val overlayImageView: ImageView = viewBinding.includedLayout.overlayImageView
    private val globalOverlayView: View = viewBinding.globalOverlayView
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

    private var currentExchanges = emptyList<ExchangeListItem>()

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

    private val fastOutLinearInInterpolator: Interpolator by lazy {
        AnimationUtils.loadInterpolator(context, android.R.interpolator.fast_out_linear_in)
    }

    init {
        onBackPressedInvoker.action = {
            dispatch(ViewEvent.PairSelectionStateChanged(false))
        }

        addPairPanel.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                setupBaseMotionParams()
                setupAddPairViewState()
                addPairPanel.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

        exchangeTextView.setOnClickListener { dispatch(ViewEvent.ExchangesDialogRequested) }
        globalOverlayView.setOnClickListener { dispatch(ViewEvent.PairSelectionStateChanged(false)) }
        tickersFab.setOnClickListener { dispatch(ViewEvent.PairSelectionStateChanged(true)) }

        addPairButton.setOnClickListener {
            dispatch(ViewEvent.AddNewTicker)
            dispatch(ViewEvent.PairSelectionStateChanged(false))
        }

        globalOverlayView.setOnTouchListener { _, event ->
            var flag = false
            if (event.action == MotionEvent.ACTION_DOWN) {
                flag = globalOverlayView.measuredHeight - event.y < addPairPanel.measuredHeight
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
        diff(get = ViewModel::isExchangesDialogActive, set = ::showExchangeSelectionDialog)
        diff(get = ViewModel::isPairSelectionViewActive, set = ::showPairSelectionView)
        diff(get = ViewModel::isAddButtonEnabled, set = addPairButton::setEnabled)
    }

    private fun showSelectedExchange(exchanges: List<ExchangeListItem>) {
        exchanges.find { it.isSelected }?.let { exchangeTextView.text = it.exchange.label }
        currentExchanges = exchanges
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

    private fun showExchangeSelectionDialog(show: Boolean) {
        if (show) {
            val initialIndex = currentExchanges.indexOfFirst { it.isSelected }

            MaterialDialog(context).show {
                listItemsSingleChoice(
                    items = currentExchanges.map { it.exchange.label },
                    initialSelection = initialIndex,
                    waitForPositiveButton = true,
                    selection = { _, index, _ ->
                        dispatch(ViewEvent.ExchangeSelected(currentExchanges[index].exchange))
                    }
                ).show {
                    title(R.string.label_exchange_dialog)
                }.setOnDismissListener {
                    dispatch(ViewEvent.ExchangesDialogClosed)
                }
            }
        }
    }

    private fun showPairSelectionView(show: Boolean) {
        if (isPairSelectionExpanded != show) {
            dispatch(ViewEvent.PairSelectionStateChanged(show))
            onBackPressedCallback.isEnabled = show
            changeAddPairViewExpandState()
        }
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
        defaultFabCenterX = tickersFab.centerX()
        defaultFabCenterY = tickersFab.centerY()

        dialogTranslationX = defaultFabCenterX - defaultDialogCenterX
        dialogTranslationY = defaultFabCenterY - defaultDialogCenterY
    }

    private fun setupAddPairViewState() {
        if (isPairSelectionExpanded) {
            addPairPanel.translationX = 0f
            addPairPanel.translationY = 0f

            addPairPanel.isVisible = true
            globalOverlayView.isVisible = true

            tickersFab.isGone = true
            overlayView.isGone = true
            overlayImageView.isGone = true
        } else {
            addPairPanel.translationX = dialogTranslationX
            addPairPanel.translationY = dialogTranslationY

            addPairPanel.isGone = true
            globalOverlayView.isGone = true

            tickersFab.isVisible = true
            overlayView.isVisible = true
            overlayImageView.isVisible = true
        }
    }

    private fun changeAddPairViewExpandState() {
        val set = AnimatorSet()
        set.playTogether(
            getFabArcPathAnimator(),
            getDialogArcPathAnimator(),
            getGlobalOverlayAnimator(),
            getCircularRevealAnimator(),
            getOverlayAlphaAnimator(),
            getOverlayIconAlphaAnimator()
        )
        set.addStartEndActions(
            startWith = {
                globalOverlayView.isEnabled = false
            },
            endWith = {
                globalOverlayView.isEnabled = true
                isPairSelectionExpanded = !isPairSelectionExpanded
            }
        ).start()
    }

    private fun getFabArcPathAnimator(): Animator {
        val startX = if (isPairSelectionExpanded) -dialogTranslationX else 0f
        val startY = if (isPairSelectionExpanded) -dialogTranslationY else 0f
        val endX = if (isPairSelectionExpanded) 0f else -dialogTranslationX
        val endY = if (isPairSelectionExpanded) 0f else -dialogTranslationY

        return ObjectAnimator.ofFloat(
            tickersFab,
            View.TRANSLATION_X,
            View.TRANSLATION_Y,
            ArcMotion().getPath(startX, startY, endX, endY)
        )
            .applyDefaultParams()
            .addStartEndActions(
                startWith = {
                    if (!isPairSelectionExpanded) {
                        tickersFab.isGone = true
                        addPairPanel.isVisible = true
                        overlayView.isVisible = true
                        overlayImageView.isVisible = true
                    }
                },
                endWith = {
                    if (isPairSelectionExpanded) {
                        tickersFab.isVisible = true
                        addPairPanel.isGone = true
                        overlayView.isGone = true
                        overlayImageView.isGone = true
                    }
                })
    }

    private fun getDialogArcPathAnimator(): Animator {
        val startX = if (isPairSelectionExpanded) 0f else dialogTranslationX
        val startY = if (isPairSelectionExpanded) 0f else dialogTranslationY
        val endX = if (isPairSelectionExpanded) dialogTranslationX else 0f
        val endY = if (isPairSelectionExpanded) dialogTranslationY else 0f


        return ObjectAnimator.ofFloat(
            addPairPanel,
            View.TRANSLATION_X,
            View.TRANSLATION_Y,
            ArcMotion().getPath(startX, startY, endX, endY)
        ).applyDefaultParams()
    }

    private fun getOverlayAlphaAnimator(): Animator {
        val startValue = if (isPairSelectionExpanded) 0f else 1f
        val endValue = if (isPairSelectionExpanded) 1f else 0f

        return ObjectAnimator.ofFloat(overlayView, View.ALPHA, startValue, endValue)
            .applyDefaultParams()
            .addEndAction {
                overlayView.alpha = endValue
                overlayView.isGone = isPairSelectionExpanded
            }
            .apply {
                startDelay = if (!isPairSelectionExpanded) DIALOG_OVERLAY_ANIMATION_DELAY else 0L
            }
    }

    private fun getOverlayIconAlphaAnimator(): Animator {
        val startValue = if (isPairSelectionExpanded) 0f else 1f
        val endValue = if (isPairSelectionExpanded) 1f else 0f

        return ObjectAnimator.ofFloat(overlayImageView, View.ALPHA, startValue, endValue)
            .applyDefaultParams()
            .addEndAction {
                overlayImageView.alpha = endValue
                overlayImageView.isGone = isPairSelectionExpanded
            }
    }

    private fun getGlobalOverlayAnimator(): Animator {
        val startValue = if (isPairSelectionExpanded) 1f else 0f
        val endValue = if (isPairSelectionExpanded) 0f else 1f

        return ObjectAnimator.ofFloat(globalOverlayView, View.ALPHA, startValue, endValue)
            .applyDefaultParams()
            .addStartEndActions(
                startWith = {
                    globalOverlayView.alpha = startValue
                    if (!isPairSelectionExpanded) globalOverlayView.isVisible = true
                },
                endWith = {
                    globalOverlayView.alpha = endValue
                    if (isPairSelectionExpanded) globalOverlayView.isGone = true
                }
            )
    }

    private fun getCircularRevealAnimator(): Animator {
        val startRadius = if (isPairSelectionExpanded) addPairPanel.height.toFloat() else tickersFab.width / 2f
        val endRadius = if (isPairSelectionExpanded) tickersFab.width / 2f else addPairPanel.height.toFloat()

        return ViewAnimationUtils.createCircularReveal(
            addPairPanel,
            addPairPanel.width / 2,
            addPairPanel.height / 2,
            startRadius,
            endRadius
        ).applyDefaultParams()
    }

    private fun Animator.applyDefaultParams(startDelay: Long = 0) =
        apply {
            this.interpolator = fastOutLinearInInterpolator
            this.duration = DIALOG_ANIMATION_DURATION
            this.startDelay = startDelay
        }

    private companion object {
        private const val DIALOG_ANIMATION_DURATION = 250L
        private const val DIALOG_OVERLAY_ANIMATION_DELAY = 150L
    }
}
