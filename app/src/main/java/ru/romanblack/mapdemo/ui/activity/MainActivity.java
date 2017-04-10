package ru.romanblack.mapdemo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.List;

import ru.romanblack.mapdemo.R;
import ru.romanblack.mapdemo.ui.fragment.MainFragment;
import ru.romanblack.mapdemo.ui.fragment.MapRootFragment;

public class MainActivity extends BaseActivity {

    private MainFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            setupFragment();
        } else {
            findFragment();
        }
    }

    @Override
    public void onBackPressed() {
        if (!fragment.goBack()) {
            super.onBackPressed();
        }
    }

    private void setupFragment() {
        fragment = new MapRootFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, fragment)
                .commit();
    }

    private void findFragment() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();

        if (fragments != null && fragments.size() > 0) {
            fragment = (MainFragment) fragments.get(0);
        }
    }

}
