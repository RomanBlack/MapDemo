package ru.romanblack.mapdemo.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.romanblack.mapdemo.App;
import ru.romanblack.mapdemo.util.NetworkUtils;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public NetworkUtils provideNetworkUtils(App app) {
        return new NetworkUtils(app);
    }
}
