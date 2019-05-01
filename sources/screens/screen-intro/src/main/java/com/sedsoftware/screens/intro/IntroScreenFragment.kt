package com.sedsoftware.screens.intro

import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import com.sedsoftware.core.di.coordinator.IntroCoordinator
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.params.DownloadState
import com.sedsoftware.core.presentation.extension.addEndAction
import com.sedsoftware.core.presentation.extension.addStartAction
import com.sedsoftware.core.presentation.extension.failure
import com.sedsoftware.core.presentation.extension.gone
import com.sedsoftware.core.presentation.extension.observe
import com.sedsoftware.core.presentation.extension.show
import com.sedsoftware.core.presentation.extension.viewModel
import com.sedsoftware.core.utils.common.Failure
import com.sedsoftware.screens.intro.adapter.ExchangesAdapter
import com.sedsoftware.screens.intro.di.IntroScreenComponent
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
            observe(loadersList, ::displayLoadersList)
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

        exchangesAdapter.clickListener = { exchange ->
            introViewModel.onExchangeClicked(exchange)
        }

        setupViewPositions()
        animateViews()
    }

    private fun setupViewPositions() {
        logo.gone()
        logo.alpha = 0f
        greetings_text.alpha = 0f
        greetings_note.alpha = 0f
        intro_button_continue.gone()
        intro_button_continue.alpha = 0f
        intro_button_continue.translationY = 100f
    }

    private fun animateViews() {
        var currentDelay = START_ANIMATION_DELAY

        logo.animate()
            .alpha(1f)
            .setInterpolator(LinearInterpolator())
            .setDuration(ANIMATION_DURATION)
            .setStartDelay(currentDelay)
            .addStartAction { logo.show() }

        currentDelay += ANIMATION_DURATION

        greetings_text.animate()
            .alpha(1f)
            .setDuration(ANIMATION_DURATION)
            .setStartDelay(currentDelay)
            .addEndAction { introViewModel.showExchanges() }

        intro_button_continue.animate()
            .alpha(1f)
            .translationY(0f)
            .setDuration(ANIMATION_DURATION)
            .setStartDelay(currentDelay)
            .addStartAction { intro_button_continue.show() }

        currentDelay += ANIMATION_DURATION

        greetings_note.animate()
            .alpha(1f)
            .setInterpolator(LinearInterpolator())
            .setDuration(ANIMATION_DURATION)
            .setStartDelay(currentDelay)
            .start()
    }

    private fun displayLoadersList(exchanges: Map<Exchange, DownloadState>?) {
        exchanges?.let { exchangesAdapter.items = it }
    }

    private fun displayFailure(failure: Failure?) {

    }

    private companion object {
        const val START_ANIMATION_DELAY = 50L
        const val ANIMATION_DURATION = 250L
    }
}
