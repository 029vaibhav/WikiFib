package com.teamie.wikifib.playscreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.teamie.wikifib.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends Fragment {

    public static final String TAG = "PlayFragment";
    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;
    Unbinder unbinder;


    public PlayFragment() {
        // Required empty public constructor
    }

    public static PlayFragment getInstance() {
        return new PlayFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_blank, container, false);
        unbinder = ButterKnife.bind(this, viewGroup);


        return viewGroup;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
