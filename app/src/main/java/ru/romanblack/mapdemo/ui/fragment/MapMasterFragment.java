package ru.romanblack.mapdemo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import ru.romanblack.mapdemo.R;
import ru.romanblack.mapdemo.data.entity.Place;
import ru.romanblack.mapdemo.di.componenet.DaggerMapMasterFragmentComponent;
import ru.romanblack.mapdemo.di.componenet.MapMasterFragmentComponent;
import ru.romanblack.mapdemo.di.componenet.MapRootFragmentComponent;
import ru.romanblack.mapdemo.di.module.MapMasterFragmentModule;
import ru.romanblack.mapdemo.mvp.presenter.MapMasterPresenter;
import ru.romanblack.mapdemo.mvp.view.MapMasterView;

public class MapMasterFragment
        extends
        ChildFragment
        implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnCameraChangeListener,
        MapMasterView {

    private MapMasterFragmentComponent component;

    @Inject
    MapMasterPresenter presenter;

    private GoogleMap map;

    private Map<LatLng, Place> placesMap = new HashMap<>();

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map_master, null);
    }

    @Override
    public void initializeUi(View view, Bundle savedInstanceState) {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void inject() {
        if (component == null) {
            MapRootFragmentComponent parentComponent =
                    getParentComponent(MapRootFragmentComponent.class);

            if (parentComponent == null) {
                return;
            }

            component = DaggerMapMasterFragmentComponent.builder()
                    .mapRootFragmentComponent(parentComponent)
                    .mapMasterFragmentModule(new MapMasterFragmentModule(this))
                    .build();
        }

        component.inject(this);
    }

    @Override
    public MapMasterPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnMarkerClickListener(this);
        map.setOnCameraChangeListener(this);

        presenter.onMapReady();
    }

    // MapMAsterView

    @Override
    public void setupMap(double lat, double lng, float zoom) {
        map.moveCamera(
                CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), zoom)
        );
    }

    @Override
    public void showPlaces(List<Place> places) {
        placesMap.clear();
        map.clear();

        for (Place place : places) {
            LatLng latLng = new LatLng(place.getLatitude(), place.getLongitude());

            map.addMarker(
                    new MarkerOptions()
                            .position(latLng)
                            .title(place.getName())
            );

            placesMap.put(latLng, place);
        }
    }

    // MapMAsterView ends

    // GoogleMap.OnMarkerClickListener

    @Override
    public boolean onMarkerClick(Marker marker) {
        LatLng latLng = marker.getPosition();

        Place place = placesMap.get(latLng);

        if (place != null) {
            presenter.onMarkerClicked(place);
        }

        return true;
    }

    // GoogleMap.OnMarkerClickListener ends

    // GoogleMap.OnCameraChangeListener

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        LatLng target = cameraPosition.target;

        presenter.onCameraChanged(target.latitude, target.longitude, cameraPosition.zoom);
    }

    // GoogleMap.OnCameraChangeListener ends

}
