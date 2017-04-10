package ru.romanblack.mapdemo.di.componenet;

import javax.inject.Singleton;

import dagger.Component;
import ru.romanblack.mapdemo.di.module.AppModule;
import ru.romanblack.mapdemo.di.module.ModelModule;
import ru.romanblack.mapdemo.di.module.NetworkModule;
import ru.romanblack.mapdemo.mvp.model.Model;
import ru.romanblack.mapdemo.util.NetworkUtils;

@Component(modules = {AppModule.class, ModelModule.class, NetworkModule.class})
@Singleton
public interface AppComponent {

    public Model getModel();

    public NetworkUtils getnNetworkUtils();

}
