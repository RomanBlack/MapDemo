package ru.romanblack.mapdemo.mvp.view;

import java.util.List;

import ru.romanblack.mapdemo.data.entity.Place;

public interface MapMasterView extends BaseView {

    public void setupMap(double lat, double lng, float zoom);
    public void showPlaces(List<Place> places);
}
