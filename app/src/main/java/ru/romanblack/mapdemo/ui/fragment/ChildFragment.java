package ru.romanblack.mapdemo.ui.fragment;

import android.support.v4.app.Fragment;

import ru.romanblack.mapdemo.di.componenet.MainFragmentComponent;

public abstract class ChildFragment extends BaseFragment {

    @Override
    public final void setRetainInstance(boolean retain) {

    }

    private MainFragment findRootFragment() {
        Fragment parentFragment = getParentFragment();

        while (parentFragment != null) {
            if (parentFragment instanceof MainFragment) {
                return (MainFragment) parentFragment;
            }

            parentFragment = parentFragment.getParentFragment();
        }

        return null;
    }

    public <T> T getParentComponent(Class<T> componentClass) {
        MainFragment mf = findRootFragment();

        if (mf == null) {
            return  null;
        }

        MainFragmentComponent component = mf.getComponent();

        if (component == null) {
            return null;
        }

        try {
            return componentClass.cast(component);
        } catch (ClassCastException e) {
            return null;
        }
    }

}
