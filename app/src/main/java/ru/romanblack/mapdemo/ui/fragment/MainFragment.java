package ru.romanblack.mapdemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.romanblack.mapdemo.di.componenet.MainFragmentComponent;
import ru.romanblack.mapdemo.mvp.view.MainView;
import ru.romanblack.mapdemo.ui.activity.MainActivity;

public abstract class MainFragment extends BaseFragment implements MainView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.setRetainInstance(true);
    }

    @Override
    public final void setRetainInstance(boolean retain) {

    }

    public abstract MainFragmentComponent getComponent();
}
