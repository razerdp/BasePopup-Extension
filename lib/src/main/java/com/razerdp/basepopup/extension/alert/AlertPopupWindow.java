package com.razerdp.basepopup.extension.alert;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;

import com.razerdp.basepopup.extension.BasePopupWindowExtension;
import com.razerdp.basepopup.lib.R;

import androidx.fragment.app.Fragment;

/**
 * Created by 大灯泡 on 2020/4/8.
 * alert类型
 */
public class AlertPopupWindow extends BasePopupWindowExtension<AlertParams> {
    private static final String TAG = "AlertPopupWindow";

    public AlertPopupWindow(Context context, AlertParams params) {
        super(context, params);
        setPopupGravity(Gravity.CENTER);
    }

    public AlertPopupWindow(Fragment fragment, AlertParams params) {
        super(fragment, params);
        setPopupGravity(Gravity.CENTER);
    }

    public AlertPopupWindow(Dialog dialog, AlertParams params) {
        super(dialog, params);
        setPopupGravity(Gravity.CENTER);
    }


    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.be_container_wrap);
    }

    @Deprecated
    @Override
    public void showPopupWindow(View anchorView) {
        super.showPopupWindow(anchorView);
    }

    @Deprecated
    @Override
    public void showPopupWindow(int x, int y) {
        super.showPopupWindow(x, y);
    }
}
