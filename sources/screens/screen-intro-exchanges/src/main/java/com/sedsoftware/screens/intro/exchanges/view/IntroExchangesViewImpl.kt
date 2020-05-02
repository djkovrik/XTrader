package com.sedsoftware.screens.intro.exchanges.view

import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.google.android.material.appbar.AppBarLayout
import com.sedsoftware.core.domain.provider.AssetsProvider
import com.sedsoftware.core.domain.tools.ResourceManager
import com.sedsoftware.screens.intro.exchanges.R
import com.sedsoftware.screens.intro.exchanges.databinding.FragmentIntroExchangesBinding
import com.sedsoftware.screens.intro.exchanges.store.model.ExchangeListItem
import com.sedsoftware.screens.intro.exchanges.view.IntroExchangesView.ViewEvent
import com.sedsoftware.screens.intro.exchanges.view.IntroExchangesView.ViewModel
import com.sedsoftware.screens.intro.exchanges.view.adapter.ExchangeListAdapter

class IntroExchangesViewImpl(
    viewBinding: FragmentIntroExchangesBinding,
    assetsProvider: AssetsProvider,
    resourceManager: ResourceManager
) : BaseMviView<ViewModel, ViewEvent>(), IntroExchangesView {

    private val appbar: AppBarLayout = viewBinding.appbar
    private val toolbarText: TextView = viewBinding.toolbarText
    private val recyclerView: RecyclerView = viewBinding.exchangesRecyclerView
    private val doneButton: Button = viewBinding.doneButton

    private val recyclerViewAdapter =
        ExchangeListAdapter(
            clickListener = object : ExchangeListAdapter.Listener {
                override fun onItemClick(item: ExchangeListItem) {
                    dispatch(ViewEvent.DownloadClicked(item.exchange))
                }
            },
            assetsProvider = assetsProvider
        )

    init {
        appbar.outlineProvider = null
        toolbarText.text = resourceManager.getString(R.string.app_name)
        toolbarText.gravity = Gravity.CENTER

        recyclerView.adapter = recyclerViewAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)

        doneButton.setOnClickListener {
            dispatch(ViewEvent.DoneClicked)
        }
    }

    override val renderer: ViewRenderer<ViewModel> = diff {
        diff(get = ViewModel::listItems, compare = { a, b -> a === b }, set = ::showItems)
        diff(get = ViewModel::isDoneButtonAvailable, set = ::enableDoneButton)
    }

    private fun showItems(items: List<ExchangeListItem>) {
        recyclerViewAdapter.items = items
        recyclerViewAdapter.notifyDataSetChanged()
    }

    private fun enableDoneButton(enable: Boolean) {
        if (enable) {
            doneButton.alpha = ALPHA_NORMAL
            doneButton.isEnabled = true
        } else {
            doneButton.alpha = ALPHA_GRAYED
            doneButton.isEnabled = false
        }
    }

    private companion object {
        private const val ALPHA_GRAYED = 0.7f
        private const val ALPHA_NORMAL = 1f
    }
}
