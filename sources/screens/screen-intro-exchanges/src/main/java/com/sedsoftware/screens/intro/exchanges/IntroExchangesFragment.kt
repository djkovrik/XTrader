package com.sedsoftware.screens.intro.exchanges

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.sedsoftware.core.presentation.base.BaseStartingFragment
import com.sedsoftware.core.presentation.extension.failure
import com.sedsoftware.core.presentation.extension.observe
import com.sedsoftware.core.presentation.extension.string
import com.sedsoftware.core.presentation.extension.viewModel
import com.sedsoftware.screens.intro.exchanges.adapter.ExchangeListAdapter
import com.sedsoftware.screens.intro.exchanges.model.ExchangeListItem
import kotlinx.android.synthetic.main.fragment_intro_exchanges.*
import javax.inject.Inject

class IntroExchangesFragment : BaseStartingFragment(), ExchangeListAdapter.Listener {

    companion object {
        fun newInstance(): IntroExchangesFragment =
            IntroExchangesFragment()

        private const val ALPHA_GRAYED = 0.7f
        private const val ALPHA_NORMAL = 1f
    }

    override val layoutResId: Int = R.layout.fragment_intro_exchanges

    @Inject
    lateinit var exchangesAdapter: ExchangeListAdapter

    private lateinit var introViewModel: IntroExchangesViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        introViewModel = viewModel(viewModelFactory) {
            observe(exchangeListLiveData, ::observeLoaderList)
            observe(nextButtonAvailableLiveData, ::observeNextButtonAvailability)
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
            layoutManager = LinearLayoutManager(this@IntroExchangesFragment.context)
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

    private fun observeNextButtonAvailability(shouldEnable: Boolean?) {
        if (shouldEnable == true) {
            introButton.alpha = ALPHA_NORMAL
            introButton.isEnabled = true
        } else {
            introButton.alpha = ALPHA_GRAYED
            introButton.isEnabled = false
        }
    }
}
