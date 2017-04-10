package ru.romanblack.mapdemo.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.romanblack.mapdemo.R;
import ru.romanblack.mapdemo.data.entity.Place;
import ru.romanblack.mapdemo.databinding.FragmentMapDetailsBinding;
import ru.romanblack.mapdemo.di.componenet.DaggerMapDetailsFragmentComponent;
import ru.romanblack.mapdemo.di.componenet.MapDetailsFragmentComponent;
import ru.romanblack.mapdemo.di.componenet.MapRootFragmentComponent;
import ru.romanblack.mapdemo.di.module.MapDetailsFragmentModule;
import ru.romanblack.mapdemo.mvp.presenter.MapDetailsPresenter;
import ru.romanblack.mapdemo.mvp.view.MapDetailsView;

public class MapDetailsFragment
        extends ChildFragment
        implements MapDetailsView {

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.image) ImageView imageView;
    @BindView(R.id.title) TextView titleView;
    @BindView(R.id.address) TextView addressView;
    @BindView(R.id.phone) TextView phoneView;
    @BindView(R.id.website) TextView websiteView;

    private FragmentMapDetailsBinding binding;
    private MapDetailsFragmentComponent component;

    @Inject
    MapDetailsPresenter presenter;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_map_details, container, false);

        return binding.getRoot();
    }

    @Override
    public void initializeUi(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefresh();
            }
        });
    }

    @Override
    protected void inject() {
        if (component == null) {
            MapRootFragmentComponent parentComponent =
                    getParentComponent(MapRootFragmentComponent.class);

            if (parentComponent == null) {
                return;
            }

            component = DaggerMapDetailsFragmentComponent.builder()
                    .mapRootFragmentComponent(parentComponent)
                    .mapDetailsFragmentModule(new MapDetailsFragmentModule(this))
                    .build();
        }

        component.inject(this);
    }

    @Override
    public MapDetailsPresenter getPresenter() {
        return presenter;
    }

    // MapDetailsView

    @Override
    public void bindViews(Place place) {
        binding.setPlace(place);
    }

    @Override
    public void setupImage(String url) {
        binding.setImageUrl(url);
    }

    @Override
    public void hideImage() {
        imageView.setImageResource(R.drawable.ic_photo_camera_black_72dp);
    }

    @Override
    public void showProgress() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        refreshLayout.setRefreshing(false);
    }

    // MapDetailsView ends
}
