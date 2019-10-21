package com.sedsoftware.screens.intro

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.sedsoftware.core.di.StartingFlowToolsProvider
import com.sedsoftware.core.presentation.base.BaseStartingFragment
import com.sedsoftware.core.presentation.extension.addEndAction
import com.sedsoftware.core.presentation.extension.addStartAction
import com.sedsoftware.core.presentation.extension.failure
import com.sedsoftware.core.presentation.extension.observe
import com.sedsoftware.core.presentation.extension.string
import com.sedsoftware.core.presentation.extension.viewModel
import com.sedsoftware.screens.intro.adapter.ExchangeListAdapter
import com.sedsoftware.screens.intro.di.IntroScreenComponent
import com.sedsoftware.screens.intro.model.ExchangeListItem
import kotlinx.android.synthetic.main.fragment_intro_screen.*
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import javax.inject.Inject

class IntroScreenFragment : BaseStartingFragment(), IHasComponent<IntroScreenComponent>,
    ExchangeListAdapter.Listener {

    companion object {
        fun newInstance(): IntroScreenFragment = IntroScreenFragment()

        private const val ALPHA_GRAYED = 0.7f
        private const val ALPHA_NORMAL = 1f
    }

    override val layoutResId: Int = R.layout.fragment_intro_screen

    @Inject
    lateinit var introViewModel: IntroScreenViewModel

    @Inject
    lateinit var exchangesAdapter: ExchangeListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        XInjectionManager
            .bindComponent(this)
            .inject(this)
    }

    override fun getComponent(): IntroScreenComponent {
        val startingFlowToolsProvider =
            XInjectionManager.findComponent { it is StartingFlowToolsProvider } as StartingFlowToolsProvider

        return IntroScreenComponent.Initializer.init(this, startingFlowToolsProvider)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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
            introViewModel.switchToRegularFlow()
        }

        with(exchangesRecyclerView) {
            adapter = exchangesAdapter
            layoutManager = LinearLayoutManager(this@IntroScreenFragment.context)
            setHasFixedSize(true)
        }
    }

    override fun onItemClick(item: ExchangeListItem) {
        introViewModel.onExchangeClicked(item.exchange)
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
