package com.sedsoftware.screens.intro.exchanges

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.screens.intro.exchanges.databinding.FragmentIntroExchangesBinding
import com.sedsoftware.screens.intro.exchanges.view.adapter.ExchangeListAdapter
import com.sedsoftware.screens.intro.exchanges.view.model.ExchangeListItem
import javax.inject.Inject

class IntroExchangesFragment : BaseFragment(), ExchangeListAdapter.Listener {

    companion object {
        fun newInstance(): IntroExchangesFragment =
            IntroExchangesFragment()

        private const val ALPHA_GRAYED = 0.7f
        private const val ALPHA_NORMAL = 1f
    }

    private val binding: FragmentIntroExchangesBinding get() = _binding!!
    private var _binding: FragmentIntroExchangesBinding? = null

    @Inject
    lateinit var exchangesAdapter: ExchangeListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentIntroExchangesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        appbar.outlineProvider = null
//        toolbar_text.text = string(R.string.app_name)
//        toolbar_text.gravity = Gravity.CENTER
//
//        introButton.setOnClickListener {
//            introViewModel.switchToRegularFlow()
//        }
//
//        with(exchangesRecyclerView) {
//            adapter = exchangesAdapter
//            layoutManager = LinearLayoutManager(this@IntroExchangesFragment.context)
//            setHasFixedSize(true)
//        }
//    }

    override fun onItemClick(item: ExchangeListItem) {
//        introViewModel.onExchangeClicked(item.exchange)
    }

//    private fun observeLoaderList(exchanges: List<ExchangeListItem>?) {
//        exchanges?.let { list ->
//            exchangesAdapter.items = list
//            exchangesAdapter.notifyDataSetChanged()
//        }
//    }
//
//    private fun observeNextButtonAvailability(shouldEnable: Boolean?) {
//        if (shouldEnable == true) {
//            introButton.alpha = ALPHA_NORMAL
//            introButton.isEnabled = true
//        } else {
//            introButton.alpha = ALPHA_GRAYED
//            introButton.isEnabled = false
//        }
//    }
}
