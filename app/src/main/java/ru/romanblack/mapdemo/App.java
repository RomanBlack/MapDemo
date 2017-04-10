package ru.romanblack.mapdemo;

import android.app.Application;

import ru.romanblack.mapdemo.di.componenet.AppComponent;
import ru.romanblack.mapdemo.di.componenet.DaggerAppComponent;
import ru.romanblack.mapdemo.di.module.AppModule;
import ru.romanblack.mapdemo.di.module.ModelModule;
import ru.romanblack.mapdemo.di.module.NetworkModule;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/roboto_regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        component = buildcomponent();
    }

    private AppComponent buildcomponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .modelModule(new ModelModule())
                .networkModule(new NetworkModule())
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }
}
