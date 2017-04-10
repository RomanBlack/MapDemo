package ru.romanblack.mapdemo.mvp.presenter;

import android.content.Context;
import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import ru.romanblack.mapdemo.App;
import ru.romanblack.mapdemo.R;
import ru.romanblack.mapdemo.data.entity.Place;
import ru.romanblack.mapdemo.di.componenet.DaggerMapRootPresenterComponent;
import ru.romanblack.mapdemo.mvp.model.Model;
import ru.romanblack.mapdemo.mvp.view.MapRootView;
import ru.romanblack.mapdemo.util.BaseSubscriber;
import ru.romanblack.mapdemo.util.Consts;
import ru.romanblack.mapdemo.util.NetworkUtils;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class MapRootPresenter
        extends MainPresenter
        implements
        MapMasterPresenter.ParentPresenter,
        MapDetailsPresenter.ParentPresenter{

    public MapRootPresenter(MapRootView mapRootView) {
        super(mapRootView);

        this.mapRootView = mapRootView;
    }

    @Inject
    Model model;
    @Inject
    NetworkUtils networkUtils;

    private MapRootView mapRootView;

    private MapMasterPresenter masterPresenter;
    private MapDetailsPresenter detailsPresenter;

    private Subscription placesSubscription;
    private Subscription placeDetialsSubscription;
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();

    private State state;

    @Override
    public void onAttach(Context context) {
        inject();

        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialize();
    }

    @Override
    public void onUiInitialized() {
        super.onUiInitialized();

        switch (state.page) {
            case State.PAGE_DETAILS:
                setupDetails();
                break;
            default:
                setupMain();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        compositeSubscription.unsubscribe();
    }

    @Override
    public boolean goBack() {
        if (state.page == State.PAGE_DETAILS) {
            state.detailsState = null;
            setupMain();

            return true;
        }

        return false;
    }

    private void inject() {
        DaggerMapRootPresenterComponent.builder()
                .appComponent(App.getComponent())
                .build()
                .inject(this);
    }

    private void initialize() {
        if (state == null) {
            state = new State();
        }
    }

    private void setupMain() {
        state.page = State.PAGE_MASTER;

        mapRootView.setupMain();
    }

    private void setupDetails() {
        state.page = State.PAGE_DETAILS;

        Bundle args = new Bundle();
        args.putSerializable(Consts.EXTRA_PLACE, state.detailsPlace);

        mapRootView.setupDetails(args);
    }

    // MapMasterPresenter.ParentPresenter

    @Override
    public void setMasterPresenter(MapMasterPresenter masterPresenter) {
        this.masterPresenter = masterPresenter;
    }

    @Override
    public void loadPlaces(double lat, double lng, int radius) {
        if (!networkUtils.isOnline()) {
            mapRootView.showAlert(R.string.alert_no_internet);
            return;
        }

        if (placesSubscription != null) {
            placesSubscription.unsubscribe();
        }

        placesSubscription = model
                .loadPlaces(lat, lng, radius)
                .subscribe(new BaseSubscriber<List<Place>>() {
                    @Override
                    public void completed() {
                        placesSubscription = null;
                    }

                    @Override
                    public void error(Throwable e) {
                        placesSubscription = null;

                        mapRootView.showAlert(R.string.alert_network_error);
                    }

                    @Override
                    public void next(List<Place> places) {
                        if (masterPresenter != null) {
                            masterPresenter.onPlacesLoaded(places);
                        }
                    }
                });

        compositeSubscription.add(placesSubscription);
    }

    @Override
    public void showDetails(Place place) {
        state.detailsPlace = place;

        setupDetails();
    }

    @Override
    public void setMasterState(Object state) {
        this.state.masterState = state;
    }

    @Override
    public Object getMasterState() {
        return state.masterState;
    }

    // MapMasterPresenter.ParentPresenter ends

    // MapDetailsPresenter.ParentPresenter

    @Override
    public void setDetailsPresenter(MapDetailsPresenter detailsPresenter) {
        this.detailsPresenter = detailsPresenter;
    }

    @Override
    public void loadPlaceDetails(Place place) {
        if (!networkUtils.isOnline()) {
            mapRootView.showAlert(R.string.alert_no_internet);
            return;
        }

        if (placeDetialsSubscription != null) {
            placeDetialsSubscription.unsubscribe();
        }

        placeDetialsSubscription = model
                .loadPlaceDetails(place.getPlaceId())
                .subscribe(new BaseSubscriber<Place>(){
                    @Override
                    public void completed() {
                        placeDetialsSubscription = null;
                    }

                    @Override
                    public void error(Throwable e) {
                        placeDetialsSubscription = null;

                        mapRootView.showAlert(R.string.alert_network_error);
                    }

                    @Override
                    public void next(Place place) {
                        if (detailsPresenter != null) {
                            detailsPresenter.onPlaceLoaded(place);
                        }
                    }
                });

        compositeSubscription.add(placeDetialsSubscription);
    }

    @Override
    public void setDetailsState(Object state) {
        this.state.detailsState = state;
    }

    @Override
    public Object getDetailsState() {
        return state.detailsState;
    }

    // MapDetailsPresenter.ParentPresenter ends

    private static class State {

        public static final int PAGE_MASTER = 0;
        public static final int PAGE_DETAILS = 1;

        private int page = PAGE_MASTER;

        private Place detailsPlace;

        private Object masterState;
        private Object detailsState;
    }
}
