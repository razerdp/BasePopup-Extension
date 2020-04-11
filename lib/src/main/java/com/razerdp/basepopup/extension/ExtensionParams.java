package com.razerdp.basepopup.extension;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * Created by 大灯泡 on 2020/4/8.
 */
public abstract class ExtensionParams {
    //只是临时使用~
    protected Object windowParent;
    protected BasePopupWindowExtension cachedPopup;

    protected ExtensionParams() {
    }

    public ExtensionParams(Context context) {
        this.windowParent = context;
    }

    public ExtensionParams(Fragment fragment) {
        this.windowParent = fragment;
    }

    public ExtensionParams(Dialog dialog) {
        this.windowParent = dialog;
    }

    public void onDestroy() {
        cachedPopup = null;
        windowParent = null;
    }

    @NonNull
    protected abstract BasePopupWindowExtensionAdapter getExtensionAdapter();

    public static class DefaultConfig {

    }
}
