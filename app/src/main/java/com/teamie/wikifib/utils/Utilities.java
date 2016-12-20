package com.teamie.wikifib.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * Created by vaibhav on 20/12/16.
 */

public class Utilities {


    public static TextView CreateTextView(Context context) {
        TextView mType = new TextView(context);

        mType.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT, 1f));
        mType.setTextSize(17);
        mType.setPadding(5, 3, 0, 3);
        mType.setTypeface(Typeface.DEFAULT_BOLD);
        mType.setGravity(Gravity.LEFT | Gravity.CENTER);
        return mType;
    }

}
