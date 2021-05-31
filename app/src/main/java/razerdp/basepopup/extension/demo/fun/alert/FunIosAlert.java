package razerdp.basepopup.extension.demo.fun.alert;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import razerdp.basepopup.extension.PopupProviders;
import razerdp.basepopup.extension.alert.AlertInterface;
import razerdp.basepopup.extension.alert.AlertParams;
import razerdp.basepopup.extension.alert.AlertPopupWindow;
import razerdp.basepopup.extension.demo.fun.FunContent;
import razerdp.basepopup.extension.demo.utils.UIHelper;

/**
 * Created by 大灯泡 on 2021/5/31.
 */
public class FunIosAlert extends FunContent {
    public FunIosAlert() {
        setTitle("iOS样式弹窗");
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
