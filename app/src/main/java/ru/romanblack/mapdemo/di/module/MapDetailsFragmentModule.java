package ru.romanblack.mapdemo.di.module;

import dagger.Module;
import dagger.Provides;
import ru.romanblack.mapdemo.di.scope.ChildFragmentScope;
import ru.romanblack.mapdemo.mvp.view.MapDetailsView;
import ru.romanblack.mapdemo.ui.fragment.MapDetailsFragment;

@Module
public class MapDetailsFragmentModule {

    public MapDetailsFragmentModule(MapDetailsFragment fragment) {
        this.fragment = fragment;
    }

    private MapDetailsFragment fragment;

    @Provides
    @ChildFragmentScope
    public MapDetailsView getView() {
        return fragment;
    }
}
