package com.teamie.wikifib.gameengine.textrazor;

import com.teamie.wikifib.bean.GameData;
import com.teamie.wikifib.gameengine.factories.LogicFactory;
import com.teamie.wikifib.gameengine.interfaces.WordExtractor;
import com.teamie.wikifib.level.LevelDecider;
import com.teamie.wikifib.level.TextRazorLevelDecider;
import com.teamie.wikifib.utils.Constants;
import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;
import com.textrazor.TextRazor;
import com.textrazor.annotations.AnalyzedText;
import com.textrazor.annotations.Response;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by vaibhav on 21/12/16.
 */

public class WordExtractorImpl implements WordExtractor<AnalyzedText> {


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

        LevelDecider levelDecider = new TextRazorLevelDecider();
        List<String> criteria = levelDecider.levelCriteria(level);
        AnalyzedText analyzedText = getDataInBackground(s, criteria);
        Response response = analyzedText.getResponse();
        GameData gameData = LogicFactory.getInstance().LogicFactory().getGameData(response, s);
        return gameData;
    }

    private AnalyzedText getDataInBackground(final String s, final List<String> criteria) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable callable = new Callable() {
            @Override
            public AnalyzedText call() throws Exception {
                return extractWords(s, criteria);
            }
        };

        Future submit = executorService.submit(callable);
        try {
            AnalyzedText o = (AnalyzedText) submit.get();
            return o;
        } catch (InterruptedException | ExecutionException e) {
        }
        return null;
    }
}