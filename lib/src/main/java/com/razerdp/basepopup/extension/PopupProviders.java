package com.razerdp.basepopup.extension;

import android.app.Dialog;
import android.content.Context;

import com.razerdp.basepopup.extension.alert.AlertParams;

import androidx.fragment.app.Fragment;

/**
 * Created by 大灯泡 on 2020/4/8.
 * <p>
 * popup工厂
 */
public class PopupProviders {

    public static AlertParams alert(Context windowParent) {
        return new AlertParams(windowParent);
    }

    public static AlertParams alert(Fragment windowParent) {
        return new AlertParams(windowParent);
    }

    public static AlertParams alert(Dialog windowParent) {
        return new AlertParams(windowParent);
    }
}
