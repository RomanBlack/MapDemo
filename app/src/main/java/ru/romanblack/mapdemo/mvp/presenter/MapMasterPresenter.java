package ru.romanblack.mapdemo.mvp.presenter;

import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import ru.romanblack.mapdemo.data.entity.Place;
import ru.romanblack.mapdemo.mvp.view.MapMasterView;
import ru.romanblack.mapdemo.util.Consts;

public class MapMasterPresenter extends BasePresenter {

    @Inject
    public MapMasterPresenter(MapMasterView view, ParentPresenter parentPresenter) {
        super(view);

        this.view = view;
        this.parentPresenter = parentPresenter;
    }

    private MapMasterView view;

    private ParentPresenter parentPresenter;

    private State state;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parentPresenter.setMasterPresenter(this);

        initializeData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        parentPresenter.setMasterPresenter(null);
    }

    private void initializeData() {
        if (parentPresenter != null) {
            state = (State) parentPresenter.getMasterState();

            if (state == null) {
                state = new State();
                parentPresenter.setMasterState(state);
            }
        }
    }

    public void onMapReady() {
        view.setupMap(state.lat, state.lng, state.zoom);

        if (state.currentPlaces == null) {
            parentPresenter.loadPlaces(state.lat, state.lng, Consts.POI_RADIUS);
        } else {
            view.showPlaces(state.currentPlaces);
        }
    }

    public void onCameraChanged(double lat, double lng, float zoom) {
        state.lat = lat;
        state.lng = lng;
        state.zoom = zoom;

        parentPresenter.loadPlaces(state.lat, state.lng, Consts.POI_RADIUS);
    }

    public void onMarkerClicked(Place place) {
        parentPresenter.showDetails(place);
    }

    public void onPlacesLoaded(List<Place> places) {
        state.currentPlaces = places;

        view.showPlaces(places);
    }

    public interface ParentPresenter extends ru.romanblack.mapdemo.mvp.presenter.ParentPresenter {

        public void setMasterPresenter(MapMasterPresenter masterPresenter);

        public void setMasterState(Object state);
        public Object getMasterState();

        public void loadPlaces(double lat, double lng, int radius);

        public void showDetails(Place place);

    }

    private static class State {

        double lat = Consts.DEFAULT_LAT;
        double lng = Consts.DEFAULT_LON;

        float zoom = Consts.DEFAULT_ZOOM;

        List<Place> currentPlaces;
    }
}
