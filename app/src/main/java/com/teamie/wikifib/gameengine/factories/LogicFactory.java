package com.teamie.wikifib.gameengine.factories;

import com.teamie.wikifib.gameengine.defaultimpl.DefaultLogicDecider2;
import com.teamie.wikifib.gameengine.interfaces.LogicDecider;

/**
 * Created by vaibhav on 22/12/16.
 */
public class LogicFactory {


    private static LogicFactory ourInstance;

    public static LogicFactory getInstance() {

        if (ourInstance == null)
            ourInstance = new LogicFactory();
        return ourInstance;
    }

    public LogicDecider LogicFactory() {

        LogicDecider logicDecider = new DefaultLogicDecider2();
        return logicDecider;
    }
}
