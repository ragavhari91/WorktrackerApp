package com.app.worktrackerapp.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by ragavendran on 02-Nov-2015.
 */
public class CustomProgressDialog
{
    public void showProgress(String message,Context context)
    {
        ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage(message);
        pDialog.show();
    }

    public void hideProgress(Context context)
    {
        ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.hide();
    }

}
