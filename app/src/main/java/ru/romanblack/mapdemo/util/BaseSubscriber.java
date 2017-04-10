package ru.romanblack.mapdemo.util;

import android.util.Log;

import ru.romanblack.mapdemo.BuildConfig;
import rx.Subscriber;

public class BaseSubscriber<T> extends Subscriber<T> {

    @Override
    public final void onCompleted() {
        completed();
    }

    @Override
    public final void onError(Throwable e) {
        if (BuildConfig.DEBUG) {
            Log.e(Consts.LOG_ERROR_TAG, "ERROR!", e);
        }

        error(e);
    }

    @Override
    public final void onNext(T t) {
        next(t);
    }

    public void completed() {}

    public void error(Throwable e) {}

    public void next(T t) {}
}
