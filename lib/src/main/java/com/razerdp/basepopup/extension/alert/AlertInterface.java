package com.razerdp.basepopup.extension.alert;

import android.view.View;

/**
 * Created by 大灯泡 on 2020/4/8.
 */
public interface AlertInterface {
    int BUTTON_POSITIVE = -1;
    int BUTTON_NEGATIVE = -2;
    int BUTTON_NEUTRAL = -3;

    abstract class OnClickListener {
        abstract void onPositiveClick(View v);

        abstract void onNegativeClick(View v);

        abstract void onNeutralClick(View v);
    }
}
