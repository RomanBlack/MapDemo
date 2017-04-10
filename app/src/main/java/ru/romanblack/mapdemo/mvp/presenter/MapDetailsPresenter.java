package ru.romanblack.mapdemo.mvp.presenter;

import android.os.Bundle;

import javax.inject.Inject;

import ru.romanblack.mapdemo.data.entity.Place;
import ru.romanblack.mapdemo.mvp.view.MapDetailsView;
import ru.romanblack.mapdemo.util.Consts;

public class MapDetailsPresenter extends BasePresenter{

    @Inject
    public MapDetailsPresenter(MapDetailsView view, ParentPresenter parentPresenter) {
        super(view);

        this.view = view;
        this.parentPresenter = parentPresenter;
    }

    private MapDetailsView view;

    private ParentPresenter parentPresenter;

    private State state;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parentPresenter.setDetailsPresenter(this);

        initializeData();
    }

    @Override
    public void onUiInitialized() {
        if (state.place == null) {
            startLoad();
        } else {
            setupUi();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        parentPresenter.setDetailsPresenter(null);
    }

    public void onPlaceLoaded(Place place) {
        if (place != null) {
            this.state.place = place;
        }

        this.state.loading = false;

        setupUi();
    }

    private void initializeData() {
        Bundle args = view.getArguments();

        if (args != null) {
            Place place = (Place) args.getSerializable(Consts.EXTRA_PLACE);

            State tmpState = (State) parentPresenter.getDetailsState();

            if (tmpState != null && tmpState.initialPlace.equals(place)) {
                state = tmpState;
            } else {
                state = new State();
                state.initialPlace = place;

                parentPresenter.setDetailsState(state);
            }
        }
    }

    private void setupUi() {
        if (state.loading) {
            view.showProgress();
        } else {
            view.hideProgress();
        }

        Place place = state.place;

        if (place == null) {
            return;
        }

        view.bindViews(place);

        if (place.getPhotos() != null && place.getPhotos().size() > 0) {
            Place.Photo photo = place.getPhotos().get(0);

            String imageUrl = Consts.PLACES_API_URL
                    + "photo?"
                    + "key="
                    + Consts.PLACES_API_KEY
                    + "&"
                    + "photoreference="
                    + photo.getReference()
                    + "&"
                    + "maxheight="
                    + photo.getHeight()
                    + "&"
                    + "maxwidth="
                    + photo.getWidth();

            view.setupImage(imageUrl);
        } else {
            view.hideImage();
        }
    }

    private void startLoad() {
        view.showProgress();
        state.loading = true;

        parentPresenter.loadPlaceDetails(state.initialPlace);
    }

    public void onRefresh() {
        startLoad();
    }

    public interface ParentPresenter extends ru.romanblack.mapdemo.mvp.presenter.ParentPresenter{

        public void setDetailsPresenter(MapDetailsPresenter detailsPresenter);

        public void loadPlaceDetails(Place place);

        public Object getDetailsState();
        public void setDetailsState(Object state);
    }

    private static class State {

        private Place initialPlace;
        private Place place;

        private boolean loading;
    }
}
