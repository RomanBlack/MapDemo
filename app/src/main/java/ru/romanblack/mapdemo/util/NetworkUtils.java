package ru.romanblack.mapdemo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.romanblack.mapdemo.App;
import ru.romanblack.mapdemo.data.network.PlacesApiService;

public class NetworkUtils {

    public NetworkUtils(App app) {
        this.app = app;
    }

    private App app;

    public Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Consts.PLACES_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public PlacesApiService buildService() {
        return buildRetrofit().create(PlacesApiService.class);
    }

    public boolean isOnline() {
        ConnectivityManager connMgr =
                (ConnectivityManager) app.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
