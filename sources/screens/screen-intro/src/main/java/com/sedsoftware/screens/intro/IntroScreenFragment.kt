package com.sedsoftware.screens.intro

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.core.presentation.extension.addEndAction
import com.sedsoftware.core.presentation.extension.addStartAction
import com.sedsoftware.core.presentation.extension.failure
import com.sedsoftware.core.presentation.extension.observe
import com.sedsoftware.core.presentation.extension.string
import com.sedsoftware.core.presentation.extension.viewModel
import com.sedsoftware.screens.intro.adapter.ExchangeListAdapter
import com.sedsoftware.screens.intro.model.ExchangeListItem
import kotlinx.android.synthetic.main.fragment_intro_screen.*
import javax.inject.Inject

class IntroScreenFragment : BaseFragment(), ExchangeListAdapter.Listener {

    companion object {

        fun newInstance(): IntroScreenFragment = IntroScreenFragment()

        private const val START_ANIMATION_DELAY = 100L
        private const val ANIMATION_DURATION = 250L
        private const val TRANSLATION_DEFAULT = 0f
        private const val BASE_VIEW_TRANSLATION = 100f
        private const val ALPHA_ZERO = 0f
        private const val ALPHA_GRAYED = 0.7f
        private const val ALPHA_NORMAL = 1f
    }

    override val layoutResId: Int = R.layout.fragment_intro_screen

    private lateinit var introViewModel: IntroScreenViewModel


    @Inject
    lateinit var exchangesAdapter: ExchangeListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        introViewModel = viewModel(viewModelFactory) {
            observe(exchangeList, ::observeLoaderList)
            observe(anyDownloadCompleted, ::observeDownloadCompletion)
            failure(viewModelFailure, ::observeFailures)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appbar.outlineProvider = null
        toolbar_text.text = string(R.string.app_name)
        toolbar_text.gravity = Gravity.CENTER
        introButton.setOnClickListener {
            // FIXME Navigate home
        }

        with(exchangesRecyclerView) {
            adapter = exchangesAdapter
            layoutManager = LinearLayoutManager(this@IntroScreenFragment.context)
            setHasFixedSize(true)
        }

        introViewModel.animateAtStart {
            setupViewPositions()
            animateViews()
        }
    }

//    override fun inject(component: IntroScreenComponent) =
//        component.inject(this)
//
//    override fun provideComponent(): IntroScreenComponent =
//        IntroScreenComponent.Initializer.init(this, (parentFragment as FlowFragment).flowToolsProvider)

    override fun onItemClick(item: ExchangeListItem) {
        introViewModel.onExchangeClicked(item.exchange)
    }

    private fun setupViewPositions() {
        greetingsTextView.alpha = ALPHA_ZERO
        greetingsNoteTextView.alpha = ALPHA_ZERO
        introButton.isGone = true
        introButton.alpha = ALPHA_ZERO
        introButton.translationY = BASE_VIEW_TRANSLATION
    }

    private fun animateViews() {
        var currentDelay = START_ANIMATION_DELAY

        greetingsTextView.animate()
            .alpha(ALPHA_NORMAL)
            .setDuration(ANIMATION_DURATION)
            .setStartDelay(currentDelay)
            .addEndAction { introViewModel.showInitialList() }

        introButton.animate()
            .alpha(ALPHA_GRAYED)
            .translationY(TRANSLATION_DEFAULT)
            .setDuration(ANIMATION_DURATION)
            .setStartDelay(currentDelay)
            .addStartAction { introButton.isVisible = true }

        currentDelay += ANIMATION_DURATION

        greetingsNoteTextView.animate()
            .alpha(ALPHA_NORMAL)
            .setInterpolator(LinearInterpolator())
            .setDuration(ANIMATION_DURATION)
            .setStartDelay(currentDelay)
            .start()
    }

    private fun observeLoaderList(exchanges: List<ExchangeListItem>?) {
        exchanges?.let { list ->
            exchangesAdapter.items = list
            exchangesAdapter.notifyDataSetChanged()
        }
    }

    private fun observeDownloadCompletion(shouldEnable: Boolean?) {
        if (shouldEnable == true) {
            introButton.alpha = ALPHA_NORMAL
            introButton.isEnabled = true
        } else {
            introButton.alpha = ALPHA_GRAYED
            introButton.isEnabled = false
        }
    }
}
