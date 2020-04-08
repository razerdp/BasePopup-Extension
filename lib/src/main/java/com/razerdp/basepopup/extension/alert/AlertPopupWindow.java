package com.razerdp.basepopup.extension.alert;

import android.app.Dialog;
import android.content.Context;
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

    public AlertPopupWindow(Context context) {
        super(context);
    }

    public AlertPopupWindow(Fragment fragment) {
        super(fragment);
    }

    public AlertPopupWindow(Dialog dialog) {
        super(dialog);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.basepopup_extension_container_wrap);
    }

}
