package com.razerdp.basepopup.extension.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.NonNull;

/**
 * Created by 大灯泡 on 2020/4/11.
 */
public class ViewUtils {
    public static View removeFromParent(@NonNull View v, boolean preventRequestLayout) {
        ViewParent p = v.getParent();
        if (p instanceof ViewGroup) {
            if (preventRequestLayout) {
                ((ViewGroup) p).removeViewInLayout(v);
            } else {
                ((ViewGroup) p).removeView(v);
            }
        }
        return v;
    }

    public static void setViewsVisible(final int visible, View... views) {
        for (final View view : views) {
            if (view == null) continue;
            if (ToolUtils.isMainThread()) {
                if (visible == View.GONE) {
                    view.clearAnimation();
                }
                view.setVisibility(visible);
            } else {
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        if (visible == View.GONE) {
                            view.clearAnimation();
                        }
                        view.setVisibility(visible);
                    }
                });
            }
        }
    }
}
