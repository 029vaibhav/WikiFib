package com.teamie.wikifib.gameengine.factories;

import com.teamie.wikifib.gameengine.defaultimpl.DefaultWordExtractorImpl;
import com.teamie.wikifib.gameengine.interfaces.WordExtractor;
import com.teamie.wikifib.gameengine.textrazor.TextRazaorWordExtractorImpl;

/**
 * Created by vaibhav on 21/12/16.
 */

public class WordExtractorFactory {

    public static WordExtractorFactory factory;

    public static WordExtractorFactory getInstance() {

        if (factory == null) {
            factory = new WordExtractorFactory();
        }
        return factory;
    }

    public WordExtractor getImplementation() {
        WordExtractor wordExtractor = new TextRazaorWordExtractorImpl();
        return wordExtractor;
    }
}
