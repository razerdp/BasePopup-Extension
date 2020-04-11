package com.razerdp.basepopup.extension.alert;

import android.view.View;

import androidx.annotation.NonNull;

/**
 * Created by 大灯泡 on 2020/4/8.
 */
public interface AlertInterface {
    abstract class OnClickListener {
        /**
         * 防止被子类重写，导致无法分发到指定方法
         */
        final void onAlertButtonClickInternal(@NonNull AlertPopupWindow popupWindow, @AlertParams.ButtonMode int mode, View v) {
            onAlertButtonClick(popupWindow, mode, v);
            switch (mode) {
                case AlertParams.MODE_POSITIVE:
                    onPositiveClick(popupWindow, v);
                    break;
                case AlertParams.MODE_NEUTRAL:
                    onNeutralClick(popupWindow, v);
                    break;
                case AlertParams.MODE_NEGATIVE:
                    onNegativeClick(popupWindow, v);
                    break;
            }
        }

        public void onAlertButtonClick(@NonNull AlertPopupWindow popupWindow, @AlertParams.ButtonMode int mode, View v) {
        }

        public void onPositiveClick(@NonNull AlertPopupWindow popupWindow, View v) {
        }

        public void onNegativeClick(@NonNull AlertPopupWindow popupWindow, View v) {
        }

        public void onNeutralClick(@NonNull AlertPopupWindow popupWindow, View v) {
        }
    }
}
