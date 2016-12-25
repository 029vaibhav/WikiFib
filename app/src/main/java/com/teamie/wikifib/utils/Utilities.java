package com.teamie.wikifib.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.teamie.wikifib.R;
import com.teamie.wikifib.home.HomeActivity;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by vaibhav on 20/12/16.
 */

public class Utilities {

    public static Utilities utilities;
    private ProgressDialog progressDialog;

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

    public void saveInSharedPreference(String key, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(getContext().getString(R.string.app_name), MODE_PRIVATE).edit();
        editor.putInt(key, value).apply();
    }

    public int getFromSharedPreference(String key) {
        SharedPreferences editor = context.getSharedPreferences(getContext().getString(R.string.app_name), MODE_PRIVATE);
        int level = editor.getInt(key, 0);
        return level;

    }

    public void ShowDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading");
        }
        progressDialog.show();
    }

    public void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    public void toast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void showAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("ERROR !!");
        builder.setMessage("Sorry there was an error getting data from the Internet.\nNetwork Unavailable!");
        builder.setCancelable(false);
        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(context, HomeActivity.class);
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
