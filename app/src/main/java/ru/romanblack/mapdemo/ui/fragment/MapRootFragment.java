package ru.romanblack.mapdemo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ru.romanblack.mapdemo.R;
import ru.romanblack.mapdemo.di.componenet.DaggerMapRootFragmentComponent;
import ru.romanblack.mapdemo.di.componenet.MainFragmentComponent;
import ru.romanblack.mapdemo.di.componenet.MapRootFragmentComponent;
import ru.romanblack.mapdemo.di.module.MapRootFragmentModule;
import ru.romanblack.mapdemo.mvp.presenter.MapRootPresenter;
import ru.romanblack.mapdemo.mvp.view.MapRootView;

public class MapRootFragment
        extends MainFragment
        implements MapRootView {

    @Inject
    MapRootPresenter presenter;

    private MapRootFragmentComponent component;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map_root, null);
    }

    @Override
    public void initializeUi(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void inject() {
        if (component == null) {
            component = DaggerMapRootFragmentComponent.builder()
                    .mapRootFragmentModule(new MapRootFragmentModule(this))
                    .build();
        }

        component.inject(this);
    }

    @Override
    public MainFragmentComponent getComponent() {
        return component;
    }

    @Override
    public MapRootPresenter getPresenter() {
        return presenter;
    }

    // MapRootView

    @Override
    public void setupMain() {
        MapMasterFragment fragment = new MapMasterFragment();

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

    @Override
    public void setupDetails(Bundle args) {
        MapDetailsFragment fragment = new MapDetailsFragment();
        fragment.setArguments(args);

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

    // MapRootView ends

}
