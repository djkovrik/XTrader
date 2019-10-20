package com.sedsoftware.screens.wallet

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.sedsoftware.core.presentation.base.BaseFragment

class WalletTabContainerFragment : BaseFragment() {

    companion object {
        fun newInstance(): WalletTabContainerFragment = WalletTabContainerFragment()
    }

    override val layoutResId: Int = R.layout.layout_container

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.material_color_pink_500))
    }
}
