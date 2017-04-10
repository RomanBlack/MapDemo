package ru.romanblack.mapdemo.mvp.view;

import android.os.Bundle;

public interface BaseView {

    public Bundle getArguments();
    public void showAlert(int resId);
}
