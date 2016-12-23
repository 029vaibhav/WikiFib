package com.teamie.wikifib.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teamie.wikifib.bean.enums.TextType;

import java.util.HashMap;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * Created by vaibhav on 21/12/16.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameData {

    HashMap<Integer, MyWord> removedWords = new HashMap<>();
    HashMap<Integer, MyWord> userSelectedWords = new HashMap<>();
    String text;
    TextType textType;

    HashMap<Integer, String> textViewHashMap = new HashMap<>();
    HashMap<Integer, String> editTextHashMap = new HashMap<>();


}
