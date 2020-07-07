package com.razerdp.basepopup.extension.demo.fun.alert;

import android.view.View;
import android.widget.TextView;

import com.razerdp.basepopup.extension.PopupProviders;
import com.razerdp.basepopup.extension.alert.AlertInterface;
import com.razerdp.basepopup.extension.alert.AlertParams;
import com.razerdp.basepopup.extension.alert.AlertPopupWindow;
import com.razerdp.basepopup.extension.demo.fun.FunContent;
import com.razerdp.basepopup.extension.demo.utils.UIHelper;

import androidx.annotation.NonNull;

/**
 * Created by 大灯泡 on 2020/4/11.
 */
public class FunAlert extends FunContent {
    public FunAlert() {
        title = "iOS样式弹窗";
    }

    @Override
    public void toShow(View v) {
        PopupProviders.alert(v.getContext())
                .setStyle(AlertParams.AlertStyle.IOS)
                .setTitle("Alert")
                .setMessage("Here is a message where we can put absolutely anything we want.")
                .setPositiveText("OK")
                .setNeutralText("Middle")
                .setNegativeText("Cancel")
                .setOnClickListener(new AlertInterface.OnClickListener() {
                    @Override
                    public void onAlertButtonClick(@NonNull AlertPopupWindow popupWindow, int mode, View v) {
                        if (v instanceof TextView) {
                            UIHelper.toast(((TextView) v).getText().toString());
                        }
                        popupWindow.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void toOption(View v) {

    }
}
