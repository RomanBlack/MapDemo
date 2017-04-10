package ru.romanblack.mapdemo.mvp.view;

import android.os.Bundle;

public interface MapRootView extends MainView {

    public void setupMain();
    public void setupDetails(Bundle args);
}
