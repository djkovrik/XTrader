package com.sedsoftware.screens.intro

import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import at.wirecube.additiveanimations.additive_animator.AdditiveAnimator
import com.sedsoftware.core.di.coordinator.IntroCoordinator
import com.sedsoftware.core.domain.entity.Exchange
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.custom.DownloadState
import com.sedsoftware.core.presentation.extension.failure
import com.sedsoftware.core.presentation.extension.gone
import com.sedsoftware.core.presentation.extension.observe
import com.sedsoftware.core.presentation.extension.show
import com.sedsoftware.core.presentation.extension.viewModel
import com.sedsoftware.core.utils.common.Failure
import com.sedsoftware.screens.intro.adapter.ExchangesAdapter
import com.sedsoftware.screens.intro.di.IntroScreenComponent
import com.sedsoftware.screens.intro.viewmodel.IntroScreenViewModel
import kotlinx.android.synthetic.main.fragment_intro_screen.greetings_note
import kotlinx.android.synthetic.main.fragment_intro_screen.greetings_text
import kotlinx.android.synthetic.main.fragment_intro_screen.intro_button_continue
import kotlinx.android.synthetic.main.fragment_intro_screen.intro_exchange_list
import kotlinx.android.synthetic.main.fragment_intro_screen.logo
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

        setupPreAnimationViewPositions()
        animateViews()
    }

    private fun setupPreAnimationViewPositions() {

        logo.gone()
        logo.alpha = 0f
        greetings_text.alpha = 0f
        greetings_note.alpha = 0f
        intro_button_continue.gone()
        intro_button_continue.alpha = 0f
        intro_button_continue.translationY = 100f
    }

    private fun animateViews() {
        AdditiveAnimator()
            .setStartDelay(ANIMATIONS_DELAY)
            .setDuration(ANIMATIONS_DURATION)
            .setInterpolator(LinearInterpolator())
            .target(logo)
            .addStartAction { logo.show() }
            .alpha(1f)
            .then()
            .target(greetings_text)
            .alpha(1f)
            .then()
            .target(intro_button_continue)
            .addStartAction { intro_button_continue.show() }
            .translationY(0f)
            .alpha(1f)
            .then()
            .target(greetings_note)
            .alpha(1f)
            .start()

        introViewModel.showExchanges()
    }

    private fun displayLoadersList(exchanges: Map<Exchange, DownloadState>?) {
        exchanges?.let { exchangesAdapter.items = it }
    }

    private fun displayFailure(failure: Failure?) {

    }

    private companion object {
        const val ANIMATIONS_DELAY = 50L
        const val ANIMATIONS_DURATION = 250L
    }
}
