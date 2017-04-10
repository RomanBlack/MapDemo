package ru.romanblack.mapdemo.di.module;

import dagger.Module;
import dagger.Provides;
import ru.romanblack.mapdemo.di.scope.ChildFragmentScope;
import ru.romanblack.mapdemo.mvp.view.MapMasterView;
import ru.romanblack.mapdemo.ui.fragment.MapMasterFragment;

@Module
public class MapMasterFragmentModule {

    public MapMasterFragmentModule(MapMasterFragment fragment) {
        this.fragment = fragment;
    }

    private MapMasterFragment fragment;

    @Provides
    @ChildFragmentScope
    public MapMasterView provideView() {
        return fragment;
    }
}
