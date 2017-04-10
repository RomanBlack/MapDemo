package ru.romanblack.mapdemo.di.module;

import dagger.Module;
import dagger.Provides;
import ru.romanblack.mapdemo.di.scope.ParentFragmentScope;
import ru.romanblack.mapdemo.mvp.presenter.MapDetailsPresenter;
import ru.romanblack.mapdemo.mvp.presenter.MapMasterPresenter;
import ru.romanblack.mapdemo.mvp.presenter.MapRootPresenter;
import ru.romanblack.mapdemo.mvp.view.MapRootView;
import ru.romanblack.mapdemo.ui.fragment.MapRootFragment;

@Module
public class MapRootFragmentModule {

    public MapRootFragmentModule(MapRootFragment fragment) {
        this.fragment = fragment;

        this.presenter = new MapRootPresenter(fragment);
    }

    private MapRootFragment fragment;
    private MapRootPresenter presenter;

    @Provides
    @ParentFragmentScope
    public MapRootView provideView() {
        return fragment;
    }

    @Provides
    @ParentFragmentScope
    public MapRootPresenter providePresenter() {
        return presenter;
    }

    @Provides
    @ParentFragmentScope
    public MapMasterPresenter.ParentPresenter provideMasterParent() {
        return presenter;
    }

    @Provides
    @ParentFragmentScope
    public MapDetailsPresenter.ParentPresenter provideDetailsParent() {
        return presenter;
    }

}
