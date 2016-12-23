package com.teamie.wikifib.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * Created by vaibhav on 20/12/16.
 */

public class Utilities {

    public static Utilities utilities;

    public static Utilities getInstance() {
        if (utilities == null)
            utilities = new Utilities();
        return utilities;
    }

    Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public TextView CreateTextView(int id, String text) {
        TextView mType = new TextView(context);

        mType.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT, 1f));
        mType.setTextSize(17);
        mType.setPadding(5, 3, 0, 3);
        mType.setTypeface(Typeface.DEFAULT_BOLD);
        mType.setGravity(Gravity.LEFT | Gravity.CENTER);
        mType.setId(id);
        mType.setText(text);
        return mType;
    }

    public Button createButton(int id, String text) {
        Button mType = new Button(context);

        mType.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT, 1f));
        mType.setTextSize(17);
        mType.setPadding(5, 3, 0, 3);
        mType.setTypeface(Typeface.DEFAULT_BOLD);
        mType.setGravity(Gravity.LEFT | Gravity.CENTER);
        mType.setId(id);
        mType.setText(text);
        return mType;
    }


}
