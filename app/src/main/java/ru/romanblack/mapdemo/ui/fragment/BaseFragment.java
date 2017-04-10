package ru.romanblack.mapdemo.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ru.romanblack.mapdemo.mvp.presenter.BasePresenter;
import ru.romanblack.mapdemo.mvp.view.BaseView;

public abstract class BaseFragment extends Fragment implements BaseView {

    BasePresenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        inject();

        presenter = getPresenter();

        if (presenter != null) {
            presenter.onAttach(context);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (presenter != null) {
            presenter.onCreate(savedInstanceState);
        }
    }

    @Nullable
    @Override
    public final View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (presenter != null) {
            presenter.onCreateView(inflater, container, savedInstanceState);
        }

        View result = createView(inflater, container, savedInstanceState);

        return result;
    }

    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (presenter != null) {
            presenter.onViewCreated(view, savedInstanceState);
        }

        initializeUi(view, savedInstanceState);

        if (presenter != null) {
            presenter.onUiInitialized();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (presenter != null) {
            presenter.onActivityCreated(savedInstanceState);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if (presenter != null) {
            presenter.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (presenter != null) {
            presenter.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (presenter != null) {
            presenter.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (presenter != null) {
            presenter.onStop();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (presenter != null) {
            presenter.onDestroyView();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (presenter != null) {
            presenter.onDestroy();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (presenter != null) {
            presenter.onDetach();
        }
    }

    public final boolean goBack() {
        if (presenter != null) {
            return presenter.goBack();
        } else {
            return false;
        }
    }

    public void showAlert(int resId) {
        Toast.makeText(getContext(), resId, Toast.LENGTH_SHORT).show();
    }

    public abstract BasePresenter getPresenter();

    public abstract View createView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract void initializeUi(View view, Bundle savedInstanceState);

    protected abstract void inject();

}
