package com.teamie.wikifib.playscreen;

import com.teamie.wikifib.interfaces.WordExtractor;

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
        WordExtractor wordExtractor = new WordExtractorImpl();
        return wordExtractor;
    }
}
