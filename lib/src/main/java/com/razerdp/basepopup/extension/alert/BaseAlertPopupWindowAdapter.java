package com.razerdp.basepopup.extension.alert;

import android.view.View;

import com.razerdp.basepopup.extension.BasePopupWindowExtensionAdapter;

import androidx.annotation.NonNull;

import static com.razerdp.basepopup.extension.alert.AlertParams.MODE_NEGATIVE;
import static com.razerdp.basepopup.extension.alert.AlertParams.MODE_NEUTRAL;
import static com.razerdp.basepopup.extension.alert.AlertParams.MODE_POSITIVE;

/**
 * Created by 大灯泡 on 2020/4/8.
 * Alert的基本adapter
 */
public abstract class BaseAlertPopupWindowAdapter extends BasePopupWindowExtensionAdapter<AlertPopupWindow> {

    protected AlertParams p;

    public BaseAlertPopupWindowAdapter(@NonNull AlertParams p) {
        this.p = p;
    }

    protected <T extends BaseAlertPopupWindowAdapter> T bindPositiveView(View v) {
        if (v != null) {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    p.onButtonClickInternal(MODE_POSITIVE, v);
                }
            });
        }
        return (T) this;
    }

    protected <T extends BaseAlertPopupWindowAdapter> T bindNeutralView(View v) {
        if (v != null) {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    p.onButtonClickInternal(MODE_NEUTRAL, v);
                }
            });
        }
        return (T) this;
    }

    protected <T extends BaseAlertPopupWindowAdapter> T bindNegativeView(View v) {
        if (v != null) {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    p.onButtonClickInternal(MODE_NEGATIVE, v);
                }
            });
        }
        return (T) this;
    }

}
