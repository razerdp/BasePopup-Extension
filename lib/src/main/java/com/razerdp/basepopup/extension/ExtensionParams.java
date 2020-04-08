package com.razerdp.basepopup.extension;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * Created by 大灯泡 on 2020/4/8.
 */
public abstract class ExtensionParams<P extends ExtensionParams, T extends BasePopupWindowExtension, A extends BasePopupWindowExtensionAdapter> {
    protected A mExtensionAdapter;

    //只是临时使用~
    Context context;
    Fragment fragment;
    Dialog dialog;

    public ExtensionParams(Context context) {
        this.context = context;
    }

    public ExtensionParams(Fragment fragment) {
        this.fragment = fragment;
    }

    public ExtensionParams(Dialog dialog) {
        this.dialog = dialog;
    }

    public P setAdapter(@NonNull A adapter) {
        this.mExtensionAdapter = adapter;
        return (P) this;
    }

    @NonNull
    public A getExtensionAdapter() {
        return mExtensionAdapter;
    }

    public T build() {
        if (onCheckParams()) {
            T popup = null;
            if (context != null) {
                popup = onCreatePopup(context);
            } else if (fragment != null) {
                popup = onCreatePopup(fragment);
            } else if (dialog != null) {
                popup = onCreatePopup(dialog);
            }
            if (popup == null) {
                throw new IllegalArgumentException("无法创建Popup,请检查是否证券传入了context、fragment或dialog之一");
            }
            popup.setParams(this);
            clear();
            return popup;
        }
        throw new IllegalArgumentException("无法通过参数检查，请查看Log");
    }

    protected boolean onCheckParams() {
        if (mExtensionAdapter == null) {
            throw new NullPointerException("必须要设置Adapter");
        }
        return true;
    }

    public void clear() {
        context = null;
        fragment = null;
        dialog = null;
    }

    protected abstract T onCreatePopup(Context context);

    protected abstract T onCreatePopup(Fragment fragment);

    protected abstract T onCreatePopup(Dialog dialog);

}
