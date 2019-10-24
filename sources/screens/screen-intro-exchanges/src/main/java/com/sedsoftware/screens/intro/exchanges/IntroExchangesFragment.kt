package com.sedsoftware.screens.intro.exchanges

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.sedsoftware.core.di.StartingFlowToolsProvider
import com.sedsoftware.core.presentation.base.BaseStartingFragment
import com.sedsoftware.core.presentation.extension.failure
import com.sedsoftware.core.presentation.extension.observe
import com.sedsoftware.core.presentation.extension.string
import com.sedsoftware.core.presentation.extension.viewModel
import com.sedsoftware.screens.intro.exchanges.adapter.ExchangeListAdapter
import com.sedsoftware.screens.intro.exchanges.di.IntroExchangesComponent
import com.sedsoftware.screens.intro.exchanges.model.ExchangeListItem
import kotlinx.android.synthetic.main.fragment_intro_exchanges.*
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import javax.inject.Inject

class IntroExchangesFragment : BaseStartingFragment(), IHasComponent<IntroExchangesComponent>,
    ExchangeListAdapter.Listener {

    companion object {
        fun newInstance(): IntroExchangesFragment =
            IntroExchangesFragment()

        private const val ALPHA_GRAYED = 0.7f
        private const val ALPHA_NORMAL = 1f
    }

    override val layoutResId: Int = R.layout.fragment_intro_exchanges

    @Inject
    lateinit var introViewModel: IntroExchangesViewModel

    @Inject
    lateinit var exchangesAdapter: ExchangeListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        XInjectionManager
            .bindComponent(this)
            .inject(this)
    }

    override fun getComponent(): IntroExchangesComponent {
        val startingFlowToolsProvider =
            XInjectionManager.findComponent { it is StartingFlowToolsProvider } as StartingFlowToolsProvider

        return IntroExchangesComponent.Initializer.init(this, startingFlowToolsProvider)
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
