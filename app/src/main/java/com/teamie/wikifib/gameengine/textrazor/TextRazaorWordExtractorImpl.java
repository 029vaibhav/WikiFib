package com.teamie.wikifib.gameengine.textrazor;

import com.teamie.wikifib.bean.GameData;
import com.teamie.wikifib.gameengine.factories.LogicFactory;
import com.teamie.wikifib.gameengine.interfaces.WordExtractor;
import com.teamie.wikifib.level.LevelDecider;
import com.teamie.wikifib.level.TextRazorLevelDecider;
import com.teamie.wikifib.utils.Constants;
import com.teamie.wikifib.utils.Utilities;
import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;
import com.textrazor.TextRazor;
import com.textrazor.annotations.AnalyzedText;
import com.textrazor.annotations.Response;

import java.util.List;

/**
 * Created by vaibhav on 21/12/16.
 */

public class TextRazaorWordExtractorImpl implements WordExtractor<AnalyzedText> {


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
        if (analyzedText == null) {
            Utilities.getInstance().showAlertDialog();
            return null;
        }
        Response response = analyzedText.getResponse();
        GameData gameData = LogicFactory.getInstance().LogicFactory().getGameData(response, s);
        return gameData;
    }

    private AnalyzedText getDataInBackground(final String s, final List<String> criteria) {


        return extractWords(s, criteria);
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        Callable callable = new Callable() {
//            @Override
//            public AnalyzedText call() throws Exception {
//                return;
//            }
//        };
//
//        Future submit = executorService.submit(callable);
//        try {
//            AnalyzedText o = (AnalyzedText) submit.get();
//            return o;
//        } catch (InterruptedException | ExecutionException e) {
//        }
//        return null;
    }
}