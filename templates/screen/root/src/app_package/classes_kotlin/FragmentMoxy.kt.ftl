package ${screenPackageName}.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ${screenPackageName}.R
import ${screenPackageName}.view.${screenViewClass}
import ${screenPackageName}.presenter.${screenPresenterClass}

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arellomobile.mvp.MvpAppCompatFragment

import javax.inject.Inject

class ${screenClass}: MvpAppCompatFragment(), ${screenViewClass} {

    @Inject
    @InjectPresenter
    lateinit var presenter : ${screenPresenterClass}

    @ProvidePresenter
    fun providePresenter(): ${screenPresenterClass} = presenter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun layoutRes() = R.layout.${screenLayoutName}
}
