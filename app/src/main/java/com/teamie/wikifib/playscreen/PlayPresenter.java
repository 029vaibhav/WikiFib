package com.teamie.wikifib.playscreen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.teamie.wikifib.R;
import com.teamie.wikifib.bean.GameData;
import com.teamie.wikifib.bean.MyWord;
import com.teamie.wikifib.gameengine.factories.WordExtractorFactory;
import com.teamie.wikifib.gameengine.interfaces.WordExtractor;
import com.teamie.wikifib.interfaces.AsyncListener;
import com.teamie.wikifib.network.DataFromServerAsync;

import org.apmem.tools.layouts.FlowLayout;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.teamie.wikifib.utils.Constants.BLANK;

/**
 * Created by vaibhav on 20/12/16.
 */

public class PlayPresenter {

    private static PlayPresenter playPresenter;
    private Context context;
    private GameData gameData;
    private FlowLayout view;
    private String[] stringList;
    private String black = "#000000";


    private PlayPresenter(Context context) {
        this.context = context;
    }

    public static PlayPresenter getInstance(Context context) {
        if (playPresenter == null)
            playPresenter = new PlayPresenter(context);
        return playPresenter;
    }

    private String getDataFromServer() {
        return PlayModel.getInstance().getDataFromServer();

    }

    void CreateGame(FlowLayout view, AsyncListener asyncListener) {

        this.view = view;
        DataFromServerAsync dataFromServerAsync = new DataFromServerAsync(asyncListener, context);
        dataFromServerAsync.execute();
    }

    public GameData getGameData() {
        return gameData;
    }


    private ClickableSpan getClickableSpan(final int index, final int pstart,
                                           final int pend, final Editable spans, final String[] stringList) {
        return new ClickableSpan() {
            int start = pstart;
            int end = pend;

            @Override
            public void onClick(View widget) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(
                        context);
                builder.setItems(stringList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String s = stringList[i];
                        spans.replace(start, end, s);
                        end = start + s.length();
                        spans.setSpan(this, start, end,
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        gameData.getUserSelectedWords().put(index, new MyWord(start, end, s));

                    }
                });
                builder.create().show();
            }
        };
    }


    private TextView createTextView(int id, String text, String color) {
        TextView mType = new TextView(context);
        mType.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        mType.setTextSize(17);
        mType.setPadding(5, 3, 0, 3);
        mType.setTypeface(Typeface.DEFAULT_BOLD);
        mType.setId(id);
        mType.setText(text, TextView.BufferType.EDITABLE);
        mType.setTextColor(Color.parseColor(color));
        return mType;
    }


    GameData analyzeData() {

        String dataFromServer = getDataFromServer();
        if (dataFromServer == null) {
            return null;
        }
        WordExtractor implementation = WordExtractorFactory.getInstance().getImplementation();
        GameData gameData = implementation.manipulateAndConvertData(dataFromServer, 14);
        return gameData;
    }

    GameData validate() {

        if (gameData.getUserSelectedWords().size() < 10) {
            Toast.makeText(context, "Please fill all blanks", Toast.LENGTH_SHORT).show();
            return null;
        }
        return gameData;


    }

    public void populateDetails(GameData gameData) {
        if (gameData == null)
            return;
        this.gameData = gameData;
        int i = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Fill your blank");
        Set<String> stringSet = new HashSet<>();
        for (Map.Entry<Integer, MyWord> integerStringEntry : gameData.getRemovedWords().entrySet()) {
            stringSet.add(integerStringEntry.getValue().getText());
            i++;
        }
        stringList = stringSet.toArray(new String[stringSet.size()]);


        for (i = 0; i < 10; i++) {

            String string = gameData.getTextViewHashMap().get(i);
            int index = string.indexOf(BLANK, 0);
            TextView textView = createTextView(1000 + i, string, black);
            Editable spans = (Editable) textView.getText();
            ClickableSpan clickSpan = getClickableSpan(i, index, index + 7, spans, stringList);
            spans.setSpan(clickSpan, index, index + 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            view.addView(textView);
        }
    }

    public void setLevel(TextView textView) {
        textView.setText(String.format(context.getString(R.string.level), PlayModel.getInstance().getLevel()));
    }

}
