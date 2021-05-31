package razerdp.basepopup.extension.alert;

import android.view.View;

import razerdp.basepopup.extension.BasePopupWindowExtensionAdapter;

import androidx.annotation.NonNull;

import static razerdp.basepopup.extension.alert.AlertParams.MODE_NEGATIVE;
import static razerdp.basepopup.extension.alert.AlertParams.MODE_NEUTRAL;
import static razerdp.basepopup.extension.alert.AlertParams.MODE_POSITIVE;

/**
 * Created by 大灯泡 on 2020/4/8.
 * Alert的基本adapter
 */
public abstract class BaseAlertPopupWindowAdapter extends BasePopupWindowExtensionAdapter<AlertPopupWindow> {

    protected AlertParams p;
    protected View.OnClickListener onPositiveClickListener;
    protected View.OnClickListener onNeutralClickListener;
    protected View.OnClickListener onNegativeClickListener;


    public BaseAlertPopupWindowAdapter(@NonNull AlertParams p) {
        this.p = p;
    }

    @Override
    protected final void onBeforeShow() {
        int buttonModeFlag = p.buttonMode & AlertParams.MODE_MASK;
        onPositiveViewVisibleChange((buttonModeFlag & MODE_POSITIVE) != 0);
        onNeutralViewVisibleChange((buttonModeFlag & MODE_NEUTRAL) != 0);
        onNegativeViewVisibleChange((buttonModeFlag & MODE_NEGATIVE) != 0);
        onPreShow(p);
    }

    protected void onPreShow(AlertParams p) {

    }

    protected <T extends BaseAlertPopupWindowAdapter> T bindPositiveView(View v) {
        if (v != null) {
            if (onPositiveClickListener == null) {
                onPositiveClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        p.onButtonClickInternal(getPopupWindow(), MODE_POSITIVE, v);
                    }
                };
            }
            v.setOnClickListener(onPositiveClickListener);
        }
        return (T) this;
    }

    protected <T extends BaseAlertPopupWindowAdapter> T bindNeutralView(View v) {
        if (v != null) {
            if (onNeutralClickListener == null) {
                onNeutralClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        p.onButtonClickInternal(getPopupWindow(), AlertParams.MODE_NEUTRAL, v);
                    }
                };
            }
            v.setOnClickListener(onNeutralClickListener);
        }
        return (T) this;
    }

    protected <T extends BaseAlertPopupWindowAdapter> T bindNegativeView(View v) {
        if (v != null) {
            if (onNegativeClickListener == null) {
                onNegativeClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        p.onButtonClickInternal(getPopupWindow(), AlertParams.MODE_NEGATIVE, v);
                    }
                };
            }
            v.setOnClickListener(onNegativeClickListener);
        }
        return (T) this;
    }

    protected void onPositiveViewVisibleChange(boolean isShow) {

    }

    protected void onNeutralViewVisibleChange(boolean isShow) {

    }

    protected void onNegativeViewVisibleChange(boolean isShow) {

    }
}
