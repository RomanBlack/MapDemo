package ru.romanblack.mapdemo.mvp.view;

import ru.romanblack.mapdemo.data.entity.Place;

public interface MapDetailsView extends BaseView {

    public void bindViews(Place place);
    public void setupImage(String url);
    public void hideImage();
    public void showProgress();
    public void hideProgress();
}
