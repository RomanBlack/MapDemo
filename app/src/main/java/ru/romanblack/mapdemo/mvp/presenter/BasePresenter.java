package ru.romanblack.mapdemo.mvp.presenter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.romanblack.mapdemo.mvp.view.BaseView;

public abstract class BasePresenter {

    public BasePresenter(BaseView baseView) {
        this.baseView = baseView;
    }

    private BaseView baseView;

    public void onAttach(Context context) {
    }

    public void onCreate(Bundle savedInstanceState) {
    }

    public void onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
    }

    public void onUiInitialized() {
    }

    public void onActivityCreated(Bundle savedInstanceState) {
    }

    public void onStart() {
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onStop() {
    }

    public void onDestroyView() {
    }

    public void onDestroy() {
    }

    public void onDetach() {
    }

    public boolean goBack() {
        return false;
    }
}
