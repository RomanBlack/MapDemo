package ru.romanblack.mapdemo.mvp.model;

import java.util.ArrayList;
import java.util.List;

import ru.romanblack.mapdemo.data.entity.Place;
import ru.romanblack.mapdemo.data.network.PlacesApiService;
import ru.romanblack.mapdemo.data.network.response.PlaceDetailsResponse;
import ru.romanblack.mapdemo.data.network.response.PlacesSearchResponse;
import ru.romanblack.mapdemo.util.Consts;
import ru.romanblack.mapdemo.util.NetworkUtils;
import ru.romanblack.mapdemo.util.ObservableUtils;
import rx.Observable;
import rx.functions.Func1;

public class ModelImpl implements Model {

    public ModelImpl(NetworkUtils networkUtils) {
        placesApiService = networkUtils.buildService();
    }

    private PlacesApiService placesApiService;

    @Override
    public Observable<List<Place>> loadPlaces(double lat, double lng, int radius) {
        String key = Consts.PLACES_API_KEY;
        String location = lat + "," + lng;

        return placesApiService
                .nearbySearch(key, location, radius)
                .map(new Func1<PlacesSearchResponse, List<Place>>() {
                    @Override
                    public List<Place> call(PlacesSearchResponse placesSearchResponse) {
                        if (placesSearchResponse != null
                                && placesSearchResponse.getResults() != null) {
                            return placesSearchResponse.getResults();
                        }

                        return new ArrayList<>();
                    }
                })
                .compose(ObservableUtils.applyIoSchedulers());
    }

    @Override
    public Observable<Place> loadPlaceDetails(String placeId) {
        return placesApiService
                .placeDetails(Consts.PLACES_API_KEY, placeId)
                .map(new Func1<PlaceDetailsResponse, Place>() {
                    @Override
                    public Place call(PlaceDetailsResponse response) {
                        if (response != null) {
                            return response.getResult();
                        }

                        return null;
                    }
                })
                .compose(ObservableUtils.applyIoSchedulers());
    }
}
