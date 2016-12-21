package com.teamie.wikifib.level;

import java.util.List;

/**
 * Created by vaibhav on 21/12/16.
 */

public interface LevelDecider {


    List<String> levelCriteria(int level);

    List<String> wordsAsFib(Object... params);


}
