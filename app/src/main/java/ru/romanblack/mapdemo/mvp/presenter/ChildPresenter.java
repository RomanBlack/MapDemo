package ru.romanblack.mapdemo.mvp.presenter;

import ru.romanblack.mapdemo.mvp.view.BaseView;

public abstract class ChildPresenter<T extends ParentPresenter> extends BasePresenter {

    public ChildPresenter(BaseView baseView) {
        super(baseView);
    }
}
