package com.razerdp.basepopup.extension;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import razerdp.basepopup.BasePopupWindow;

/**
 * Created by 大灯泡 on 2020/4/8.
 */
public abstract class BasePopupWindowExtension<P extends ExtensionParams> extends BasePopupWindow {

    P mExtensionParams;

    public BasePopupWindowExtension(Context context) {
        super(context);
    }

    public BasePopupWindowExtension(Fragment fragment) {
        super(fragment);
    }

    public BasePopupWindowExtension(Dialog dialog) {
        super(dialog);
    }

    public final void setParams(@NonNull P params) {
        this.mExtensionParams = params;
        onApplyParams(params);
    }

    protected void onApplyParams(@NonNull P params) {
        params.getExtensionAdapter().onAttachPopupWindow(this);
        ViewGroup vg = (ViewGroup) getContentView();
        View v = params.getExtensionAdapter().onCreateContentView(vg);
        ViewParent parent = v.getParent();
        if (parent instanceof ViewGroup && parent != vg) {
            ((ViewGroup) parent).removeViewInLayout(v);
            vg.addView(vg);
        }
    }
}
