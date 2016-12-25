package com.teamie.wikifib.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamie.wikifib.R;
import com.teamie.wikifib.playscreen.PlayFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

    public static String TAG = "BaseFragment";
    Unbinder unbinder;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_base, container, false);
        unbinder = ButterKnife.bind(this, viewGroup);
        return viewGroup;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.play)
    void fabOnclick() {

        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(PlayFragment.TAG);
        if (fragment == null)
            fragment = PlayFragment.getInstance();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, PlayFragment.TAG).commit();
    }


}
