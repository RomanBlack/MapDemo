package ru.romanblack.mapdemo.di.componenet;

import dagger.Component;
import ru.romanblack.mapdemo.di.module.MapDetailsFragmentModule;
import ru.romanblack.mapdemo.di.scope.ChildFragmentScope;
import ru.romanblack.mapdemo.ui.fragment.MapDetailsFragment;
import ru.romanblack.mapdemo.ui.fragment.MapMasterFragment;

@Component(
        dependencies = {MapRootFragmentComponent.class},
        modules = {MapDetailsFragmentModule.class}
)
@ChildFragmentScope
public interface MapDetailsFragmentComponent {

    void inject(MapDetailsFragment fragment);
}
