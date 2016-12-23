package com.teamie.wikifib.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.teamie.wikifib.R;
import com.teamie.wikifib.playscreen.PlayFragment;

import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new BaseFragment(), BaseFragment.TAG).commit();
    }


}
