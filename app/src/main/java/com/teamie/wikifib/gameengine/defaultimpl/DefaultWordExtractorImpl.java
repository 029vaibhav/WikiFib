package com.teamie.wikifib.gameengine.defaultimpl;

import com.teamie.wikifib.bean.GameData;
import com.teamie.wikifib.gameengine.factories.LogicFactory;
import com.teamie.wikifib.gameengine.interfaces.WordExtractor;
import com.teamie.wikifib.utils.Constants;
import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;
import com.textrazor.TextRazor;
import com.textrazor.annotations.AnalyzedText;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by vaibhav on 21/12/16.
 */

public class DefaultWordExtractorImpl implements WordExtractor<AnalyzedText> {


    @Override
    public AnalyzedText extractWords(String s, List<String> extractor) {

        TextRazor client = new TextRazor(Constants.API);
        for (String s1 : extractor) {
            client.addExtractor(s1);
        }
        try {
            return client.analyze(s);
        } catch (NetworkException | AnalysisException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GameData manipulateAndConvertData(final String s, int level) {


        GameData gameData = LogicFactory.getInstance().LogicFactory().getGameData(null, s);
        return gameData;
    }


}