package ${screenPackageName}.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ${screenPackageName}.R
import ${corePackageName}.base.BaseFragment
import ${screenPackageName}.view.${screenViewClass}
import ${screenPackageName}.presenter.${screenPresenterClass}

import javax.inject.Inject;

class ${fragmentClass}: BaseFragment<${screenPresenterClass}, ${screenViewClass}>(), ${screenViewClass} {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun layoutRes() = R.layout.${screenLayoutName}
}
