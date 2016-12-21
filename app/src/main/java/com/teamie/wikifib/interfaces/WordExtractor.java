package com.teamie.wikifib.interfaces;

import com.teamie.wikifib.bean.GameData;

import java.util.List;

/**
 * Created by vaibhav on 21/12/16.
 */

public interface WordExtractor<T> {

    T extractWords(String s, List<String> criteria);

    List<GameData> manipulateAndConvertData(String s, int level);
}
