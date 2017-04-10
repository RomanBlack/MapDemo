package ru.romanblack.mapdemo.di.componenet;

import dagger.Component;
import ru.romanblack.mapdemo.di.module.MapRootFragmentModule;
import ru.romanblack.mapdemo.di.scope.ParentFragmentScope;
import ru.romanblack.mapdemo.mvp.presenter.MapDetailsPresenter;
import ru.romanblack.mapdemo.mvp.presenter.MapMasterPresenter;
import ru.romanblack.mapdemo.ui.fragment.MapRootFragment;

@ParentFragmentScope
@Component(modules = {MapRootFragmentModule.class})
public interface MapRootFragmentComponent extends MainFragmentComponent {

    void inject(MapRootFragment fragment);

    public MapMasterPresenter.ParentPresenter getMasterParent();
    public MapDetailsPresenter.ParentPresenter getDetailsParent();
}
