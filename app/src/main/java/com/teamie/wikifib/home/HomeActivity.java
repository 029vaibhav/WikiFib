package com.teamie.wikifib.home;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.teamie.wikifib.R;
import com.teamie.wikifib.utils.Utilities;

import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Utilities.getInstance().setContext(this);
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
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Utilities.getInstance().toast(getString(R.string.back_press));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
