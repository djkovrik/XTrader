package com.sedsoftware.screens.market

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.transition.ArcMotion
import com.sedsoftware.core.domain.entity.Currency
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.extension.addEndAction
import com.sedsoftware.core.presentation.extension.addStartEndActions
import com.sedsoftware.core.presentation.extension.centerX
import com.sedsoftware.core.presentation.extension.centerY
import com.sedsoftware.core.presentation.extension.failure
import com.sedsoftware.core.presentation.extension.observe
import com.sedsoftware.core.presentation.extension.viewModel
import com.sedsoftware.core.utils.type.Failure
import com.sedsoftware.screens.market.di.MarketScreenComponent
import kotlinx.android.synthetic.main.fragment_market_screen.*
import kotlinx.android.synthetic.main.view_add_pair.*

class MarketScreenFragment : BaseFragment() {

    private val fastOutLinearInInterpolator: Interpolator by lazy {
        AnimationUtils.loadInterpolator(context, android.R.interpolator.fast_out_linear_in)
    }

    private var dialogExpanded: Boolean = false
        get() =
            arguments?.getBoolean(DIALOG_STATE_KEY) ?: false
        set(value) {
            arguments?.putBoolean(DIALOG_STATE_KEY, value)
            field = value
        }

    private var defaultDialogCenterX = 0f
    private var defaultDialogCenterY = 0f
    private var dialogTranslationX = 0f
    private var dialogTranslationY = 0f
    private var defaultFabCenterX = 0f
    private var defaultFabCenterY = 0f

    lateinit var marketViewModel: MarketScreenViewModel

    override fun getLayoutResId(): Int =
        R.layout.fragment_market_screen

    override fun inject() {
        MarketScreenComponent.Initializer
            .init(parentActivityComponent)
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        marketViewModel = viewModel(viewModelFactory) {
            observe(exchangeList, ::showExchangeList)
            observe(baseCurrencies, ::showBaseCurrencies)
            observe(marketCurrencies, ::showMarketCurrencies)
            observe(chosenExchange, ::showChosenExchange)
            observe(chosenBaseCurrency, ::showChosenBaseCurrency)
            observe(chosenMarketCurrency, ::showChosenMarketCurrency)
            failure(viewModelFailure, ::displayFailure)
        }
    }

    private fun showExchangeList(list: List<Exchange>?) {
        Log.d("market", "showExchangeList $list")
    }

    private fun showBaseCurrencies(list: List<Currency>?) {
        Log.d("market", "showBase $list")
    }

    private fun showMarketCurrencies(list: List<Currency>?) {
        Log.d("market", "showMarketCurrencies $list")
    }

    private fun showChosenExchange(exchange: Exchange?) {
        Log.d("market", "showChosenExchange $exchange")
    }

    private fun showChosenBaseCurrency(currency: Currency?) {
        Log.d("market", "showChosenBaseCurrency $currency")
    }

    private fun showChosenMarketCurrency(currency: Currency?) {
        Log.d("market", "showChosenMarketCurrency $currency")
    }

    private fun displayFailure(failure: Failure?) {

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
    }

    override fun onBackPressed(): Boolean {
        if (dialogExpanded) {
            changeDialogExpandState()
            return true
        }

        return false
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
        if (dialogExpanded) {
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
                dialogExpanded = !dialogExpanded
            }
        ).start()
    }

    private fun getFabArcPathAnimator(): Animator {
        val startX = if (dialogExpanded) -dialogTranslationX else 0f
        val startY = if (dialogExpanded) -dialogTranslationY else 0f
        val endX = if (dialogExpanded) 0f else -dialogTranslationX
        val endY = if (dialogExpanded) 0f else -dialogTranslationY

        return ObjectAnimator.ofFloat(
            marketFab,
            View.TRANSLATION_X,
            View.TRANSLATION_Y,
            ArcMotion().getPath(startX, startY, endX, endY)
        )
            .applyDefaultParams()
            .addStartEndActions(
                startWith = {
                    if (!dialogExpanded) {
                        marketFab.isGone = true
                        addPairPanel.isVisible = true
                        overlayView.isVisible = true
                        overlayImageView.isVisible = true
                    }
                },
                endWith = {
                    if (dialogExpanded) {
                        marketFab.isVisible = true
                        addPairPanel.isGone = true
                        overlayView.isGone = true
                        overlayImageView.isGone = true
                    }
                })
    }

    private fun getDialogArcPathAnimator(): Animator {
        val startX = if (dialogExpanded) 0f else dialogTranslationX
        val startY = if (dialogExpanded) 0f else dialogTranslationY
        val endX = if (dialogExpanded) dialogTranslationX else 0f
        val endY = if (dialogExpanded) dialogTranslationY else 0f


        return ObjectAnimator.ofFloat(
            addPairPanel,
            View.TRANSLATION_X,
            View.TRANSLATION_Y,
            ArcMotion().getPath(startX, startY, endX, endY)
        ).applyDefaultParams()
    }

    private fun getOverlayAlphaAnimator(): Animator {
        val startValue = if (dialogExpanded) 0f else 1f
        val endValue = if (dialogExpanded) 1f else 0f

        return ObjectAnimator.ofFloat(overlayView, View.ALPHA, startValue, endValue)
            .applyDefaultParams()
            .addEndAction {
                overlayView.alpha = endValue
                overlayView.isGone = dialogExpanded
            }
            .apply {
                startDelay = if (!dialogExpanded) DIALOG_OVERLAY_ANIMATION_DELAY else 0L
            }
    }

    private fun getOverlayIconAlphaAnimator(): Animator {
        val startValue = if (dialogExpanded) 0f else 1f
        val endValue = if (dialogExpanded) 1f else 0f

        return ObjectAnimator.ofFloat(overlayImageView, View.ALPHA, startValue, endValue)
            .applyDefaultParams()
            .addEndAction {
                overlayImageView.alpha = endValue
                overlayImageView.isGone = dialogExpanded
            }
    }

    private fun getGlobalOverlayAnimator(): Animator {
        val startValue = if (dialogExpanded) 1f else 0f
        val endValue = if (dialogExpanded) 0f else 1f

        return ObjectAnimator.ofFloat(globalOverlayView, View.ALPHA, startValue, endValue)
            .applyDefaultParams()
            .addStartEndActions(
                startWith = {
                    globalOverlayView.alpha = startValue
                    if (!dialogExpanded) globalOverlayView.isVisible = true
                },
                endWith = {
                    globalOverlayView.alpha = endValue
                    if (dialogExpanded) globalOverlayView.isGone = true
                }
            )
    }

    private fun getCircularRevealAnimator(): Animator {
        val startRadius = if (dialogExpanded) addPairPanel.height.toFloat() else marketFab.width / 2f
        val endRadius = if (dialogExpanded) marketFab.width / 2f else addPairPanel.height.toFloat()

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

    private companion object {
        const val DIALOG_ANIMATION_DURATION = 250L
        const val DIALOG_OVERLAY_ANIMATION_DELAY = 150L
        const val ALPHA_GRAYED = 0.7f
        const val ALPHA_NORMAL = 1f

        const val DIALOG_STATE_KEY = "DIALOG_STATE_KEY"
    }

}
