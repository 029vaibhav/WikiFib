package com.teamie.wikifib.playscreen;

import android.content.Context;
import android.widget.LinearLayout;

import com.teamie.wikifib.bean.WikiResponse;

/**
 * Created by vaibhav on 20/12/16.
 */

public class PlayPresenter {

    private static PlayPresenter playPresenter;
    private final Context context;

    public PlayPresenter(Context context) {
        this.context = context;
    }

    public static PlayPresenter getInstance(Context context) {
        if (playPresenter == null)
            playPresenter = new PlayPresenter(context);
        return playPresenter;
    }

    public void getDataFromServer() {
        WikiResponse dataFromServer = PlayModel.getInstance().getDataFromServer();

    }

    public void CreateGame(LinearLayout view) {



    }
}
