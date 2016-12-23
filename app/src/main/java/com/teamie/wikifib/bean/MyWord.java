package com.teamie.wikifib.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by vaibhav on 23/12/16.
 */
@Getter
@Setter
public class MyWord {

    int start;
    int end;
    String text;

    public MyWord(int start,int end,String text) {
        this.start = start;
        this.end = end;
        this.text = text;
    }
}
