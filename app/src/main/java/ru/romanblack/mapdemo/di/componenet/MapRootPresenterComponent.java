package ru.romanblack.mapdemo.di.componenet;

import dagger.Component;
import ru.romanblack.mapdemo.di.scope.ParentPresenterScope;
import ru.romanblack.mapdemo.mvp.presenter.MapRootPresenter;

@Component(dependencies = {AppComponent.class})
@ParentPresenterScope
public interface MapRootPresenterComponent {

    void inject(MapRootPresenter presenter);
}
