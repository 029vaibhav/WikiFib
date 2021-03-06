package com.teamie.wikifib.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * Created by vaibhav on 20/12/16.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Query {

    HashMap<String, Data> pages;

}
