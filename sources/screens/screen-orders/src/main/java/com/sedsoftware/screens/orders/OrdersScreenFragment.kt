package com.sedsoftware.screens.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.screens.orders.databinding.FragmentOrdersScreenBinding


class OrdersScreenFragment : BaseFragment() {

    companion object {
        fun newInstance(): OrdersScreenFragment = OrdersScreenFragment()
    }

    private val binding: FragmentOrdersScreenBinding get() = _binding!!
    private var _binding: FragmentOrdersScreenBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentOrdersScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
