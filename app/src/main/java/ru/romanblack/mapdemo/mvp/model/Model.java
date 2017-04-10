package ru.romanblack.mapdemo.mvp.model;

import java.util.List;

import ru.romanblack.mapdemo.data.entity.Place;
import rx.Observable;

public interface Model {

    public Observable<List<Place>> loadPlaces(double lat, double lng, int radius);
    public Observable<Place> loadPlaceDetails(String placeId);
}
