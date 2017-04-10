package ru.romanblack.mapdemo.di.componenet;

import dagger.Component;
import ru.romanblack.mapdemo.di.module.MapMasterFragmentModule;
import ru.romanblack.mapdemo.di.scope.ChildFragmentScope;
import ru.romanblack.mapdemo.ui.fragment.MapMasterFragment;

@Component(
        dependencies = {MapRootFragmentComponent.class},
        modules = {MapMasterFragmentModule.class}
)
@ChildFragmentScope
public interface MapMasterFragmentComponent {

    void inject(MapMasterFragment fragment);
}
