package com.sedsoftware.screens.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.screens.wallet.databinding.FragmentWalletScreenBinding

class WalletScreenFragment : BaseFragment() {

    companion object {
        fun newInstance(): WalletScreenFragment = WalletScreenFragment()
    }

    private val binding: FragmentWalletScreenBinding get() = _binding!!
    private var _binding: FragmentWalletScreenBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWalletScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
