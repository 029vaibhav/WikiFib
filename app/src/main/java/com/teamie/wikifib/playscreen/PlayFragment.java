package com.teamie.wikifib.playscreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.teamie.wikifib.R;
import com.teamie.wikifib.bean.GameData;
import com.teamie.wikifib.interfaces.AsyncListener;
import com.teamie.wikifib.resultscreen.ResultDisplayFragment;
import com.teamie.wikifib.utils.Utilities;

import org.apmem.tools.layouts.FlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends Fragment implements AsyncListener<GameData> {

    public static final String TAG = "PlayFragment";
    @BindView(R.id.linear_layout)
    FlowLayout linearLayout;
    @BindView(R.id.text)
    TextView textView;
    Unbinder unbinder;
    @BindView(R.id.pageloader)
    RelativeLayout pageLoader;
    @BindView(R.id.image)
    ImageView imageView;


    public PlayFragment() {
        // Required empty public constructor
    }

    public static PlayFragment getInstance() {
        return new PlayFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_blank, container, false);
        unbinder = ButterKnife.bind(this, viewGroup);
        PlayPresenter.getInstance(getContext()).setLevel(textView);
        Animation animation = AnimationUtils.loadAnimation(getContext(),
                R.anim.slide);
        animation.setRepeatMode(Animation.INFINITE);
        imageView.setAnimation(animation);
        PlayPresenter.getInstance(getContext()).CreateGame(linearLayout, this);
        return viewGroup;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.submit)
    public void onClick() {

        GameData gameData = PlayPresenter.getInstance(getContext()).validate();
        if (gameData != null) {
            Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(ResultDisplayFragment.TAG);
            if (fragment == null)
                fragment = ResultDisplayFragment.newInstance(gameData);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, ResultDisplayFragment.TAG).commit();
        }
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onPostExecute(GameData gameData) {
        if (pageLoader != null)
            pageLoader.setVisibility(View.GONE);
        if (gameData == null) {
            Utilities.getInstance().showAlertDialog();
        } else {
            PlayPresenter.getInstance(getContext()).populateDetails(gameData);
        }
    }

    @Override
    public GameData doInBackground() {
        return PlayPresenter.getInstance(getContext()).analyzeData();

    }


}
