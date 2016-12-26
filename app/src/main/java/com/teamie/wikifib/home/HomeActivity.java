package com.teamie.wikifib.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.teamie.wikifib.R;
import com.teamie.wikifib.playscreen.PlayPresenter;
import com.teamie.wikifib.utils.Constants;
import com.teamie.wikifib.utils.Utilities;

import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    long back_pressed;


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        PlayPresenter.getInstance(this).destroyPresenter();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Utilities.getInstance().setContext(this);
        Constants.level = Utilities.getInstance().getFromSharedPreference(Constants.LEVEL_KEY);
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(BaseFragment.TAG);
        if (fragment == null)
            fragment = new BaseFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, BaseFragment.TAG).commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis())
            super.onBackPressed();
        else {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                Utilities.getInstance().toast(getString(R.string.back_press));
                back_pressed = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        }


    }

}
