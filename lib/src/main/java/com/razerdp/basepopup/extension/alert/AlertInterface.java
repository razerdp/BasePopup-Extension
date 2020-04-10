package com.razerdp.basepopup.extension.alert;

import android.view.View;

/**
 * Created by 大灯泡 on 2020/4/8.
 */
public interface AlertInterface {
    abstract class OnClickListener {
        /**
         * 防止被子类重写，导致无法分发到指定方法
         */
        final void onAlertButtonClickInternal(@AlertParams.ButtonMode int mode, View v) {
            onAlertButtonClick(mode, v);
            switch (mode) {
                case AlertParams.MODE_POSITIVE:
                    onPositiveClick(v);
                    break;
                case AlertParams.MODE_NEUTRAL:
                    onNeutralClick(v);
                    break;
                case AlertParams.MODE_NEGATIVE:
                    onNegativeClick(v);
                    break;
            }
        }

        public void onAlertButtonClick(@AlertParams.ButtonMode int mode, View v) {
        }

        public void onPositiveClick(View v) {
        }

        public void onNegativeClick(View v) {
        }

        public void onNeutralClick(View v) {
        }
    }
}
