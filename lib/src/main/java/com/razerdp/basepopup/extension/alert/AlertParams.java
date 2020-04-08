package com.razerdp.basepopup.extension.alert;

import android.app.Dialog;
import android.content.Context;

import com.razerdp.basepopup.extension.ExtensionParams;

import androidx.fragment.app.Fragment;

/**
 * Created by 大灯泡 on 2020/4/8.
 */
public class AlertParams extends ExtensionParams<AlertParams, AlertPopupWindow, BaseAlertPopupWindowAdapter> {
    public AlertParams(Context context) {
        super(context);
    }

    public AlertParams(Fragment fragment) {
        super(fragment);
    }

    public AlertParams(Dialog dialog) {
        super(dialog);
    }

    @Override
    protected AlertPopupWindow onCreatePopup(Context context) {
        return new AlertPopupWindow(context);
    }

    @Override
    protected AlertPopupWindow onCreatePopup(Fragment fragment) {
        return new AlertPopupWindow(fragment);
    }

    @Override
    protected AlertPopupWindow onCreatePopup(Dialog dialog) {
        return new AlertPopupWindow(dialog);
    }
}
