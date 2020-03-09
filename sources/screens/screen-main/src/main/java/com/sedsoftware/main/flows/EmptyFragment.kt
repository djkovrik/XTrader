package com.sedsoftware.main.flows

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.sedsoftware.core.presentation.base.BaseFragment
import com.sedsoftware.screens.main.R
import kotlinx.android.synthetic.main.layout_container.*

class EmptyFragment : BaseFragment() {

    override val layoutResId: Int = R.layout.layout_container

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = TextView(context)
        textView.text = "Empty"
        container.removeAllViews()
        container.addView(textView)
    }
}
