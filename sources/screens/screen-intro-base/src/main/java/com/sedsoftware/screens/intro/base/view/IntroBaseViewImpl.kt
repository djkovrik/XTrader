package com.sedsoftware.screens.intro.base.view

import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import com.google.android.material.button.MaterialButton
import com.sedsoftware.screens.intro.base.databinding.FragmentIntroBaseBinding
import com.sedsoftware.screens.intro.base.view.IntroBaseView.ViewEvent
import com.sedsoftware.screens.intro.base.view.IntroBaseView.ViewModel

class IntroBaseViewImpl(viewBinding: FragmentIntroBaseBinding) : BaseMviView<ViewModel, ViewEvent>(), IntroBaseView {

    private val downloadButton: MaterialButton = viewBinding.downloadButton
    private val nextButton: MaterialButton = viewBinding.nextButton
    private val progress: ProgressBar = viewBinding.progressBar

    init {
        downloadButton.setOnClickListener {
            dispatch(ViewEvent.DownloadClicked)
        }
    }

    override val renderer: ViewRenderer<ViewModel>? = diff {
        diff(get = ViewModel::isDownloadButtonAvailable, set = { downloadButton.isEnabled = it })
        diff(get = ViewModel::isNextButtonAvailable, set = { nextButton.isEnabled = it })
        diff(get = ViewModel::isProgressVisible, set = { progress.isVisible = it })
    }
}
