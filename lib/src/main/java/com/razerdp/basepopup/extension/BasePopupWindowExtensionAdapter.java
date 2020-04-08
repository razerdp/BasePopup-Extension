package com.razerdp.basepopup.extension;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import razerdp.basepopup.BasePopupWindow;

/**
 * Created by 大灯泡 on 2020/4/8.
 * BasePopup扩展Adapter
 */
public class BasePopupWindowExtensionAdapter<P extends BasePopupWindow> {

    private P mPopupWindow;

    public final void attachPopupWindow(@NonNull P basePopupWindow) {
        this.mPopupWindow = basePopupWindow;
        onAttachPopupWindow(basePopupWindow);
    }

    public void onAttachPopupWindow(@NonNull P basePopupWindow) {

    }

    /**
     * 返回您的ContentView
     */
    View onCreateContentView(ViewGroup parent) {
        return null;
    }

    public P getPopupWindow() {
        return mPopupWindow;
    }
}
