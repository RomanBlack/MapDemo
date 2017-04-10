package ru.romanblack.mapdemo.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.romanblack.mapdemo.App;

@Module
public class AppModule {

    public AppModule(App app) {
        this.app = app;
    }

    private App app;

    @Provides
    @Singleton
    public App provideApp() {
        return app;
    }
}
