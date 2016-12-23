package com.teamie.wikifib.gameengine.factories;

import com.teamie.wikifib.gameengine.wiki.WikiDataExtractorImpl;
import com.teamie.wikifib.gameengine.interfaces.DataExtractor;

/**
 * Created by vaibhav on 21/12/16.
 */

public class DataExtractorFactory {

    public static DataExtractorFactory factory;

    public static DataExtractorFactory getInstance() {

        if (factory == null) {
            factory = new DataExtractorFactory();
        }
        return factory;
    }

    public DataExtractor getImplementation() {
        DataExtractor dataExtractor = new WikiDataExtractorImpl();
        return dataExtractor;
    }
}
