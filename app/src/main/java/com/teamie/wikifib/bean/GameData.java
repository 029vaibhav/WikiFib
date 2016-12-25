package com.teamie.wikifib.bean;

import android.os.Parcel;
import android.os.Parcelable;

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
public class GameData implements Parcelable {

    HashMap<Integer, MyWord> removedWords = new HashMap<>();
    HashMap<Integer, MyWord> userSelectedWords = new HashMap<>();
    String text;
    HashMap<Integer, String> textViewHashMap = new HashMap<>();
    HashMap<Integer, String> editTextHashMap = new HashMap<>();


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.removedWords);
        dest.writeSerializable(this.userSelectedWords);
        dest.writeString(this.text);
        dest.writeSerializable(this.textViewHashMap);
        dest.writeSerializable(this.editTextHashMap);
    }

    public GameData() {
    }

    protected GameData(Parcel in) {
        this.removedWords = (HashMap<Integer, MyWord>) in.readSerializable();
        this.userSelectedWords = (HashMap<Integer, MyWord>) in.readSerializable();
        this.text = in.readString();
        this.textViewHashMap = (HashMap<Integer, String>) in.readSerializable();
        this.editTextHashMap = (HashMap<Integer, String>) in.readSerializable();
    }

    public static final Parcelable.Creator<GameData> CREATOR = new Parcelable.Creator<GameData>() {
        @Override
        public GameData createFromParcel(Parcel source) {
            return new GameData(source);
        }

        @Override
        public GameData[] newArray(int size) {
            return new GameData[size];
        }
    };
}
