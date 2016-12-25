package com.teamie.wikifib.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.teamie.wikifib.R;
import com.teamie.wikifib.utils.Utilities;

import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {


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
}
