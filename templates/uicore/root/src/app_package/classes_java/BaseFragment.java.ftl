package ${uiCorePackageName}.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

abstract class BaseFragment extends Fragment {

    abstract void inject()
    abstract int getLayoutId()

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inject();
        val layoutRes = getLayoutId();
        return inflater.inflate(layoutRes, container, false);
    }
}
