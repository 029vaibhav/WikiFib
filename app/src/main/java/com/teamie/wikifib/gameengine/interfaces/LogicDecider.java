package com.teamie.wikifib.gameengine.interfaces;

import com.teamie.wikifib.bean.GameData;

import java.util.List;

/**
 * Created by vaibhav on 22/12/16.
 */

public interface LogicDecider<T> {

    GameData getGameData(T t,String content);
}
