package com.teamie.wikifib.gameengine.defaultimpl;

import com.teamie.wikifib.bean.GameData;
import com.teamie.wikifib.gameengine.factories.LogicFactory;
import com.teamie.wikifib.gameengine.interfaces.WordExtractor;

import java.util.List;

/**
 * Created by vaibhav on 21/12/16.
 */

public class DefaultWordExtractorImpl implements WordExtractor<String> {


    @Override
    public String extractWords(String s, List<String> extractor) {


        return null;
    }

    @Override
    public GameData manipulateAndConvertData(final String s, int level) {


        GameData gameData = LogicFactory.getInstance().LogicFactory().getGameData(null, s);
        return gameData;
    }


}