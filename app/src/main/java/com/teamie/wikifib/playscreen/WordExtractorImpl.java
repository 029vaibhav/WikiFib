package com.teamie.wikifib.playscreen;

import com.teamie.wikifib.bean.GameData;
import com.teamie.wikifib.interfaces.WordExtractor;
import com.teamie.wikifib.level.LevelDecider;
import com.teamie.wikifib.level.TextRazorLevelDecider;
import com.teamie.wikifib.utils.Constants;
import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;
import com.textrazor.TextRazor;
import com.textrazor.annotations.AnalyzedText;
import com.textrazor.annotations.Response;

import java.util.List;

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
    public List<GameData> manipulateAndConvertData(String s, int level) {

        LevelDecider levelDecider = new TextRazorLevelDecider();
        List<String> criteria = levelDecider.levelCriteria(level);
        AnalyzedText analyzedText = extractWords(s, criteria);
        Response response = analyzedText.getResponse();
        List<String> words = levelDecider.wordsAsFib(response, criteria);
        for (String word : words) {



        }

        return null;
    }
}