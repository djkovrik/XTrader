package com.sedsoftware.screens.market

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Point
import android.os.Bundle
import android.view.Display
import android.view.MotionEvent
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.ArcMotion
import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.extension.addEndAction
import com.sedsoftware.core.presentation.extension.addStartEndActions
import com.sedsoftware.core.presentation.extension.centerX
import com.sedsoftware.core.presentation.extension.centerY
import com.sedsoftware.core.presentation.extension.failure
import com.sedsoftware.core.presentation.extension.launch
import com.sedsoftware.core.presentation.extension.observe
import com.sedsoftware.core.presentation.extension.viewModel
import com.sedsoftware.core.utils.extension.orFalse
import com.sedsoftware.screens.market.adapter.CurrencyListAdapter
import com.sedsoftware.screens.market.model.CurrencyListItem
import kotlinx.android.synthetic.main.fragment_market_screen.*
import kotlinx.android.synthetic.main.include_add_pair.*
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Named

class MarketScreenFragment : BaseFragment(), CurrencyListAdapter.Listener {

    companion object {

        fun newInstance(): MarketScreenFragment = MarketScreenFragment()

        private const val DIALOG_ANIMATION_DURATION = 250L
        private const val DIALOG_OVERLAY_ANIMATION_DELAY = 150L
        private const val ITEM_CLICK_SCROLL_DELAY = 750L
        private const val ALPHA_GRAYED = 0.7f
        private const val ALPHA_NORMAL = 1f
        private const val DIALOG_STATE_KEY = "DIALOG_STATE_KEY"
    }

    override val layoutResId: Int = R.layout.fragment_market_screen

    private val fastOutLinearInInterpolator: Interpolator by lazy {
        AnimationUtils.loadInterpolator(context, android.R.interpolator.fast_out_linear_in)
    }

    private lateinit var marketViewModel: MarketScreenViewModel

    private var isDialogExpanded: Boolean = false
    private var defaultDialogCenterX = 0f
    private var defaultDialogCenterY = 0f
    private var dialogTranslationX = 0f
    private var dialogTranslationY = 0f
    private var defaultFabCenterX = 0f
    private var defaultFabCenterY = 0f


    @Inject
    lateinit var defaultDisplay: Display

    @Inject
    @Named("base")
    lateinit var baseAdapter: CurrencyListAdapter

    @Inject
    @Named("market")
    lateinit var marketAdapter: CurrencyListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        marketViewModel = viewModel(viewModelFactory) {
            observe(chosenExchange, ::observeChosenExchange)
            observe(baseCurrencies, ::observeBaseCurrencies)
            observe(marketCurrencies, ::observeMarketCurrencies)
            observe(chosenBaseCurrency, ::observeChosenBaseCurrency)
            observe(chosenMarketCurrency, ::observeChosenMarketCurrency)
            failure(viewModelFailure, ::observeFailures)
        }

        isDialogExpanded = savedInstanceState?.getBoolean(DIALOG_STATE_KEY).orFalse()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(DIALOG_STATE_KEY, isDialogExpanded)
        super.onSaveInstanceState(outState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addPairPanel.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                setupViewParams()
                setupFabDialogState()
                addPairPanel.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

        marketFab.setOnClickListener { changeDialogExpandState() }
        globalOverlayView.setOnClickListener { changeDialogExpandState() }
        globalOverlayView.setOnTouchListener { _, event ->
            var flag = false
            if (event.action == MotionEvent.ACTION_DOWN) {
                flag = globalOverlayView.measuredHeight - event.y < addPairPanel.measuredHeight
            }
            flag
        }
        enableButton(false)
        addPairPanel.layoutParams.height = calculatePanelHeight()

        baseRecyclerView.adapter = baseAdapter
        baseRecyclerView.itemAnimator = null
        marketRecyclerView.adapter = marketAdapter
        marketRecyclerView.itemAnimator = null

        baseRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL).apply {
            ContextCompat.getDrawable(requireContext(), R.drawable.divider)?.let { setDrawable(it) }
        })

        marketRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL).apply {
            ContextCompat.getDrawable(requireContext(), R.drawable.divider)?.let { setDrawable(it) }
        })
    }

    override fun onItemClick(item: CurrencyListItem) {
        if (item.isBase) {
            marketViewModel.getMarketForChosenBase(item.currency)
            moveChosenItemUp(item)
        } else {
            marketViewModel.marketCurrencyChosen(item.currency)
        }
    }

    private fun moveChosenItemUp(item: CurrencyListItem) {
        (baseRecyclerView.layoutManager as? LinearLayoutManager)?.let { manager ->
            val first = manager.findFirstVisibleItemPosition()
            val completelyFirst = manager.findFirstCompletelyVisibleItemPosition()
            val last = manager.findLastVisibleItemPosition()
            val completelyLast = manager.findLastCompletelyVisibleItemPosition()
            val current = baseAdapter.items.indexOf(item)

            val scrollTo =
                if (last != completelyLast)
                    completelyLast + current - first
                else
                    completelyLast + current - completelyFirst

            if (scrollTo < baseAdapter.itemCount) {
                launch {
                    delay(ITEM_CLICK_SCROLL_DELAY)
                    baseRecyclerView.smoothScrollToPosition(scrollTo)
                }
            }
        }
    }

    private fun observeBaseCurrencies(list: List<CurrencyListItem>?) {
        baseAdapter.items = list
        baseAdapter.notifyDataSetChanged()
    }

    private fun observeMarketCurrencies(list: List<CurrencyListItem>?) {
        marketAdapter.items = list
        marketAdapter.notifyDataSetChanged()
    }

    private fun observeChosenExchange(exchange: Exchange?) {
        exchangeTextView.text = exchange?.label
    }

    private fun observeChosenBaseCurrency(currency: Currency?) {
        baseCurrencyTextView.text = currency?.name
        baseFullCurrencyTextView.text = currency?.label
        highlightItem(baseAdapter, currency)
    }

    private fun observeChosenMarketCurrency(currency: Currency?) {
        marketCurrencyTextView.text = currency?.name
        marketFullCurrencyTextView.text = currency?.label
        highlightItem(marketAdapter, currency)
    }

    // 3/4 of the screen height
    private fun calculatePanelHeight(): Int {
        val size = Point()
        defaultDisplay.getSize(size)
        return size.y / 5 * 4
    }

    private fun setupViewParams() {
        defaultDialogCenterX = addPairPanel.centerX()
        defaultDialogCenterY = addPairPanel.centerY()
        defaultFabCenterX = marketFab.centerX()
        defaultFabCenterY = marketFab.centerY()

        dialogTranslationX = defaultFabCenterX - defaultDialogCenterX
        dialogTranslationY = defaultFabCenterY - defaultDialogCenterY
    }

    private fun setupFabDialogState() {
        if (isDialogExpanded) {
            addPairPanel.translationX = 0f
            addPairPanel.translationY = 0f

            addPairPanel.isVisible = true
            globalOverlayView.isVisible = true

            marketFab.isGone = true
            overlayView.isGone = true
            overlayImageView.isGone = true
        } else {
            addPairPanel.translationX = dialogTranslationX
            addPairPanel.translationY = dialogTranslationY

            addPairPanel.isGone = true
            globalOverlayView.isGone = true
        }
    }

    private fun changeDialogExpandState() {
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
                isDialogExpanded = !isDialogExpanded
            }
        ).start()
    }

    private fun getFabArcPathAnimator(): Animator {
        val startX = if (isDialogExpanded) -dialogTranslationX else 0f
        val startY = if (isDialogExpanded) -dialogTranslationY else 0f
        val endX = if (isDialogExpanded) 0f else -dialogTranslationX
        val endY = if (isDialogExpanded) 0f else -dialogTranslationY

        return ObjectAnimator.ofFloat(
            marketFab,
            View.TRANSLATION_X,
            View.TRANSLATION_Y,
            ArcMotion().getPath(startX, startY, endX, endY)
        )
            .applyDefaultParams()
            .addStartEndActions(
                startWith = {
                    if (!isDialogExpanded) {
                        marketFab.isGone = true
                        addPairPanel.isVisible = true
                        overlayView.isVisible = true
                        overlayImageView.isVisible = true
                    }
                },
                endWith = {
                    if (isDialogExpanded) {
                        marketFab.isVisible = true
                        addPairPanel.isGone = true
                        overlayView.isGone = true
                        overlayImageView.isGone = true
                    }
                })
    }

    private fun getDialogArcPathAnimator(): Animator {
        val startX = if (isDialogExpanded) 0f else dialogTranslationX
        val startY = if (isDialogExpanded) 0f else dialogTranslationY
        val endX = if (isDialogExpanded) dialogTranslationX else 0f
        val endY = if (isDialogExpanded) dialogTranslationY else 0f


        return ObjectAnimator.ofFloat(
            addPairPanel,
            View.TRANSLATION_X,
            View.TRANSLATION_Y,
            ArcMotion().getPath(startX, startY, endX, endY)
        ).applyDefaultParams()
    }

    private fun getOverlayAlphaAnimator(): Animator {
        val startValue = if (isDialogExpanded) 0f else 1f
        val endValue = if (isDialogExpanded) 1f else 0f

        return ObjectAnimator.ofFloat(overlayView, View.ALPHA, startValue, endValue)
            .applyDefaultParams()
            .addEndAction {
                overlayView.alpha = endValue
                overlayView.isGone = isDialogExpanded
            }
            .apply {
                startDelay = if (!isDialogExpanded) DIALOG_OVERLAY_ANIMATION_DELAY else 0L
            }
    }

    private fun getOverlayIconAlphaAnimator(): Animator {
        val startValue = if (isDialogExpanded) 0f else 1f
        val endValue = if (isDialogExpanded) 1f else 0f

        return ObjectAnimator.ofFloat(overlayImageView, View.ALPHA, startValue, endValue)
            .applyDefaultParams()
            .addEndAction {
                overlayImageView.alpha = endValue
                overlayImageView.isGone = isDialogExpanded
            }
    }

    private fun getGlobalOverlayAnimator(): Animator {
        val startValue = if (isDialogExpanded) 1f else 0f
        val endValue = if (isDialogExpanded) 0f else 1f

        return ObjectAnimator.ofFloat(globalOverlayView, View.ALPHA, startValue, endValue)
            .applyDefaultParams()
            .addStartEndActions(
                startWith = {
                    globalOverlayView.alpha = startValue
                    if (!isDialogExpanded) globalOverlayView.isVisible = true
                },
                endWith = {
                    globalOverlayView.alpha = endValue
                    if (isDialogExpanded) globalOverlayView.isGone = true
                }
            )
    }

    private fun getCircularRevealAnimator(): Animator {
        val startRadius = if (isDialogExpanded) addPairPanel.height.toFloat() else marketFab.width / 2f
        val endRadius = if (isDialogExpanded) marketFab.width / 2f else addPairPanel.height.toFloat()

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

    private fun enableButton(shouldEnable: Boolean?) {

    }

    private fun highlightItem(adapter: CurrencyListAdapter, currency: Currency?) {
        val currentItems = adapter.items.toMutableList()
        // Deselect old
        currentItems.find { it.isSelected }?.let { item ->
            val index = currentItems.indexOf(item)
            currentItems[index].isSelected = false
            adapter.notifyItemChanged(index)
        }
        // Select new
        currentItems.find { it.currency == currency }?.let { item ->
            val index = currentItems.indexOf(item)
            currentItems[index].isSelected = true
            adapter.notifyItemChanged(index)
        }
    }
}
