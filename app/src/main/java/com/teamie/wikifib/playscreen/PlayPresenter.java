package com.teamie.wikifib.playscreen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.teamie.wikifib.R;
import com.teamie.wikifib.bean.GameData;
import com.teamie.wikifib.bean.MyWord;
import com.teamie.wikifib.gameengine.factories.WordExtractorFactory;
import com.teamie.wikifib.gameengine.interfaces.WordExtractor;

import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.teamie.wikifib.utils.Constants.BLANK;

/**
 * Created by vaibhav on 20/12/16.
 */

class PlayPresenter  {

    private static PlayPresenter playPresenter;
    private Context context;
    private GameData gameData;
    private FlowLayout view;
    private String[] stringList = new String[10];
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

    void CreateGame(FlowLayout view) {

        gameData = analyzeData();
        this.view = view;
        int i = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Fill your blank");
        for (Map.Entry<Integer, MyWord> integerStringEntry : gameData.getRemovedWords().entrySet()) {
            stringList[i] = integerStringEntry.getValue().getText();
            i++;
        }
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


    private ClickableSpan getClickableSpan(final int index, final int pstart,
                                           final int pend, final Editable spans, final String[] stringList) {
        return new ClickableSpan() {
            int start = pstart;
            int end = pend;

            @Override
            public void onClick(View widget) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
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


    private GameData analyzeData() {

        String dataFromServer = getDataFromServer();
        WordExtractor implementation = WordExtractorFactory.getInstance().getImplementation();
        GameData gameData = implementation.manipulateAndConvertData(dataFromServer, 14);
        return gameData;
    }

    void submit() {

        if (gameData.getUserSelectedWords().size() < 10) {
            Toast.makeText(context, "Please fill all blanks", Toast.LENGTH_SHORT).show();
            return;
        }

        view.removeAllViews();


        int count = 0;
        int j = 0;
        List<Spannable> spannableList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String s = gameData.getTextViewHashMap().get(i);
            Spannable wordtoSpan = new SpannableString(s);
            MyWord userSelectedWord = gameData.getUserSelectedWords().get(i);
            String correctWord = gameData.getEditTextHashMap().get(i);

            int i1 = s.indexOf(BLANK, 0);
            if (correctWord.equals(userSelectedWord.getText())) {
                count++;

                SpannableStringBuilder ssb = new SpannableStringBuilder();
                ssb.append(wordtoSpan);
                ssb.replace(i1, i1 + 7, correctWord);
                ssb.setSpan(new ForegroundColorSpan(Color.GREEN), userSelectedWord.getStart(), userSelectedWord.getEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                wordtoSpan = SpannableString.valueOf(ssb);

            } else {
                SpannableStringBuilder ssb = new SpannableStringBuilder();
                String[] split = s.split(BLANK);
                if (split.length == 1) {
                    if (i1 == 0) {
                        ssb.append(userSelectedWord.getText());
                        ssb.setSpan(new StrikethroughSpan(), 0, userSelectedWord.getText().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ssb.setSpan(new ForegroundColorSpan(Color.RED), 0, userSelectedWord.getText().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ssb.append(" " + correctWord);
                        int offset = userSelectedWord.getText().length() + 2;
                        ssb.setSpan(new ForegroundColorSpan(Color.GREEN), offset, offset + correctWord.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ssb.append(split[0]);
                    } else {
                        ssb.append(split[0]);
                        ssb.append(userSelectedWord.getText());
                        int offset = split[0].length();
                        ssb.setSpan(new StrikethroughSpan(), offset, offset + userSelectedWord.getText().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ssb.setSpan(new ForegroundColorSpan(Color.RED), offset, offset + userSelectedWord.getText().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ssb.append(" " + correctWord);
                        offset = offset + userSelectedWord.getText().length() + 2;
                        ssb.setSpan(new ForegroundColorSpan(Color.GREEN), offset, offset + correctWord.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                } else {
                    ssb.append(split[0]);
                    ssb.append(userSelectedWord.getText());
                    int offset = split[0].length();
                    ssb.setSpan(new StrikethroughSpan(), offset, offset + userSelectedWord.getText().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssb.setSpan(new ForegroundColorSpan(Color.RED), offset, offset + userSelectedWord.getText().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssb.append(correctWord);
                    offset = offset + userSelectedWord.getText().length();
                    ssb.setSpan(new ForegroundColorSpan(Color.GREEN), offset, offset + correctWord.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssb.append(split[1] );
                    wordtoSpan = SpannableString.valueOf(ssb);
                }

            }
            spannableList.add(wordtoSpan);


        }

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        for (Spannable spannable : spannableList) {
            spannableStringBuilder.append(spannable);
        }
        SpannableString spannableString = SpannableString.valueOf(spannableStringBuilder);

        TextView mType = new TextView(context);
        mType.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        mType.setTextSize(17);
        mType.setPadding(5, 3, 0, 3);
        mType.setTypeface(Typeface.DEFAULT_BOLD);

        view.addView(createTextView(200, String.format(context.getString(R.string.score), count), black));
        mType.setText(spannableString, TextView.BufferType.SPANNABLE);
        view.addView(mType);
    }
}
