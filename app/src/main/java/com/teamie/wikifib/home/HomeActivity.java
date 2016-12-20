package com.teamie.wikifib.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.teamie.wikifib.R;
import com.teamie.wikifib.playscreen.PlayFragment;

import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @OnClick(R.id.play)
    void fabOnclick() {

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(PlayFragment.TAG);
        if (fragment == null)
            fragment = PlayFragment.getInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, PlayFragment.TAG).commit();
    }

}
