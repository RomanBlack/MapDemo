package ru.romanblack.mapdemo.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.romanblack.mapdemo.mvp.model.Model;
import ru.romanblack.mapdemo.mvp.model.ModelImpl;
import ru.romanblack.mapdemo.util.NetworkUtils;

@Module
public class ModelModule {

    @Provides
    @Singleton
    public Model provideModel(NetworkUtils networkUtils) {
        return new ModelImpl(networkUtils);
    }
}
