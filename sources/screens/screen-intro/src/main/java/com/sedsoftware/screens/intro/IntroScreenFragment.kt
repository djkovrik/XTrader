package com.sedsoftware.screens.intro

import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import com.sedsoftware.core.di.coordinator.IntroCoordinator
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.extension.addEndAction
import com.sedsoftware.core.presentation.extension.addStartAction
import com.sedsoftware.core.presentation.extension.failure
import com.sedsoftware.core.presentation.extension.gone
import com.sedsoftware.core.presentation.extension.observe
import com.sedsoftware.core.presentation.extension.show
import com.sedsoftware.core.presentation.extension.string
import com.sedsoftware.core.presentation.extension.viewModel
import com.sedsoftware.core.utils.common.Failure
import com.sedsoftware.core.utils.common.Failure.LocalPersistenceError
import com.sedsoftware.core.utils.common.Failure.NetworkConnectionMissing
import com.sedsoftware.core.utils.common.Failure.ServerError
import com.sedsoftware.screens.intro.adapter.ExchangesAdapter
import com.sedsoftware.screens.intro.di.IntroScreenComponent
import com.sedsoftware.screens.intro.model.ExchangeListItem
import com.sedsoftware.screens.intro.viewmodel.IntroScreenViewModel
import kotlinx.android.synthetic.main.fragment_intro_screen.*
import javax.inject.Inject

class IntroScreenFragment : BaseFragment() {

    @Inject
    lateinit var coordinator: IntroCoordinator

    @Inject
    lateinit var exchangesAdapter: ExchangesAdapter

    private lateinit var introViewModel: IntroScreenViewModel

    override fun getLayoutResId(): Int =
        R.layout.fragment_intro_screen

    override fun inject() {
        IntroScreenComponent.Initializer.init(parentActivityComponent)
            .inject(this@IntroScreenFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        introViewModel = viewModel(viewModelFactory) {
            observe(exchangeList, ::displayLoadersList)
            observe(anyDownloadCompleted, ::enableButton)
            failure(viewModelFailure, ::displayFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        intro_button_continue.setOnClickListener { coordinator.navigateToHome() }

        with(intro_exchange_list) {
            adapter = exchangesAdapter
            layoutManager = LinearLayoutManager(this@IntroScreenFragment.context)
            setHasFixedSize(true)
        }

        exchangesAdapter.clickListener = { item ->
            introViewModel.onExchangeClicked(item.exchange)
        }

        introViewModel.animateAtStart {
            setupViewPositions()
            animateViews()
        }
    }

    private fun setupViewPositions() {
        logo.gone()
        logo.alpha = ALPHA_ZERO
        greetings_text.alpha = ALPHA_ZERO
        greetings_note.alpha = ALPHA_ZERO
        intro_button_continue.gone()
        intro_button_continue.alpha = ALPHA_ZERO
        intro_button_continue.translationY = BASE_VIEW_TRANSLATION
    }

    private fun animateViews() {
        var currentDelay = START_ANIMATION_DELAY

        logo.animate()
            .alpha(ALPHA_NORMAL)
            .setInterpolator(LinearInterpolator())
            .setDuration(ANIMATION_DURATION)
            .setStartDelay(currentDelay)
            .addStartAction { logo.show() }

        currentDelay += ANIMATION_DURATION

        greetings_text.animate()
            .alpha(ALPHA_NORMAL)
            .setDuration(ANIMATION_DURATION)
            .setStartDelay(currentDelay)
            .addEndAction { introViewModel.showInitialList() }

        intro_button_continue.animate()
            .alpha(ALPHA_GRAYED)
            .translationY(TRANSLATION_DEFAULT)
            .setDuration(ANIMATION_DURATION)
            .setStartDelay(currentDelay)
            .addStartAction { intro_button_continue.show() }

        currentDelay += ANIMATION_DURATION

        greetings_note.animate()
            .alpha(ALPHA_NORMAL)
            .setInterpolator(LinearInterpolator())
            .setDuration(ANIMATION_DURATION)
            .setStartDelay(currentDelay)
            .start()
    }

    private fun displayLoadersList(exchanges: List<ExchangeListItem>?) {
        exchanges?.let { exchangesAdapter.items = it }
    }

    private fun displayFailure(failure: Failure?) {
        when(failure) {
            is NetworkConnectionMissing -> notifyTop(string(R.string.msg_no_internet_connection))
            is LocalPersistenceError -> notifyTop(string(R.string.msg_local_error, failure.throwable.message))
            is ServerError -> notifyTop(string(R.string.msg_server_error, failure.throwable.message))
        }
    }

    private fun enableButton(shouldEnable: Boolean?) {
        if (shouldEnable == true) {
            intro_button_continue.alpha = ALPHA_NORMAL
            intro_button_continue.isEnabled = true
        } else {
            intro_button_continue.alpha = ALPHA_GRAYED
            intro_button_continue.isEnabled = false
        }
    }

    private companion object {
        const val START_ANIMATION_DELAY = 50L
        const val ANIMATION_DURATION = 250L
        const val TRANSLATION_DEFAULT = 0f
        const val BASE_VIEW_TRANSLATION = 100f
        const val ALPHA_ZERO = 0f
        const val ALPHA_GRAYED = 0.7f
        const val ALPHA_NORMAL = 1f
    }
}
