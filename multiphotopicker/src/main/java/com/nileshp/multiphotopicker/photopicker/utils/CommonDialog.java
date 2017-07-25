package com.nileshp.multiphotopicker.photopicker.utils;

import android.app.Activity;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AlertDialog.Builder;

public class CommonDialog {
    public static void showDialogConfirm(Activity activity, int message, String yesText, String noText, OnClickListener onYes, OnClickListener onNo) {
        Builder builder = new Builder(activity);
        builder.setCancelable(true);
        builder.setMessage(message);
        builder.setPositiveButton((CharSequence) yesText, onYes);
        builder.setNegativeButton((CharSequence) noText, onNo);
        builder.create().show();
    }

    public static void showInfoDialog(Activity activity, int message, String yesText, OnClickListener onYes) {
        Builder builder = new Builder(activity);
        builder.setCancelable(true);
        builder.setMessage(message);
        builder.setPositiveButton((CharSequence) yesText, onYes);
        builder.create().show();
    }
}
