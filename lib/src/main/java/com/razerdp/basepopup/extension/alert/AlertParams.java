package com.razerdp.basepopup.extension.alert;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.razerdp.basepopup.extension.BasePopupWindowExtensionAdapter;
import com.razerdp.basepopup.extension.ExtensionParams;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * Created by 大灯泡 on 2020/4/8.
 * <p>
 * 本类只关注一些参数配置，以及事件分配，具体ui渲染等逻辑交由adapter完成
 */
public class AlertParams extends ExtensionParams {
    public enum AlertStyle {
        MATERIAL,
        IOS
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({MODE_POSITIVE, MODE_NEUTRAL, MODE_NEGATIVE})
    public @interface ButtonMode {
    }

    static final int MODE_SHIFT = 1;
    public static final int MODE_POSITIVE = 0x1;
    public static final int MODE_NEUTRAL = MODE_POSITIVE << MODE_SHIFT;
    public static final int MODE_NEGATIVE = MODE_NEUTRAL << MODE_SHIFT;
    static final int MODE_MASK = MODE_POSITIVE | MODE_NEUTRAL | MODE_NEGATIVE;

    //=============================================================

    BaseAlertPopupWindowAdapter mAdapter;
    int buttonMode = MODE_POSITIVE | MODE_NEGATIVE;

    AlertInterface.OnClickListener mOnClickListener;

    CharSequence title;
    CharSequence message;
    CharSequence positiveText;
    CharSequence neutralText;
    CharSequence negativeText;

    @ColorInt
    int positiveTextColor;
    @ColorInt
    int neutralTextColor;
    @ColorInt
    int negativeTextColor;

    boolean cancelable;


    public AlertParams(Context context) {
        super(context);
    }

    public AlertParams(Fragment fragment) {
        super(fragment);
    }

    public AlertParams(Dialog dialog) {
        super(dialog);
    }

    /**
     * 样式
     */
    public AlertParams setStyle(AlertStyle type) {
        switch (type) {
            case IOS:
                setAdapter(new IOSAlertPopupWindowAdapter(this));
                break;
            case MATERIAL:
                break;
            default:
                break;
        }
        return this;
    }

    /**
     * 按键
     */
    public AlertParams buttonMode(@ButtonMode int mode) {
        this.buttonMode = mode;
        return this;
    }

    /**
     * button点击回调
     */
    public AlertParams setOnClickListener(AlertInterface.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
        return this;
    }

    @NonNull
    @Override
    protected BasePopupWindowExtensionAdapter getExtensionAdapter() {
        return mAdapter;
    }

    public AlertParams setAdapter(@NonNull BaseAlertPopupWindowAdapter adapter) {
        this.mAdapter = adapter;
        return this;
    }


    public AlertParams setTitle(CharSequence title) {
        this.title = title;
        return this;
    }

    public AlertParams setMessage(CharSequence message) {
        this.message = message;
        return this;
    }

    public AlertParams setPositiveText(CharSequence positiveText) {
        this.positiveText = positiveText;
        return this;
    }

    public AlertParams setNeutralText(CharSequence neutralText) {
        this.neutralText = neutralText;
        return this;
    }

    public AlertParams setNegativeText(CharSequence negativeText) {
        this.negativeText = negativeText;
        return this;
    }

    public AlertParams setPositiveTextColor(int positiveTextColor) {
        this.positiveTextColor = positiveTextColor;
        return this;
    }

    public AlertParams setNeutralTextColor(int neutralTextColor) {
        this.neutralTextColor = neutralTextColor;
        return this;
    }

    public AlertParams setNegativeTextColor(int negativeTextColor) {
        this.negativeTextColor = negativeTextColor;
        return this;
    }

    public AlertParams setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    void onButtonClickInternal(@ButtonMode int mode, View v) {
        if (mOnClickListener != null) {
            mOnClickListener.onAlertButtonClickInternal(mode, v);
        }
    }

    public AlertPopupWindow asPopup() {
        // TODO: 2020/4/10 优雅的实现它。。。 factory?策略模式？反射？
        if (windowParent instanceof Context) {
            cachedPopup = new AlertPopupWindow((Context) windowParent, this);
        }
        if (windowParent instanceof Fragment) {
            cachedPopup = new AlertPopupWindow((Fragment) windowParent, this);
        }
        if (windowParent instanceof Dialog) {
            cachedPopup = new AlertPopupWindow((Dialog) windowParent, this);
        }
        if (cachedPopup == null) {
            throw new IllegalArgumentException("必须传入Context、Fragment或Dialog之一哦");
        }
        return (AlertPopupWindow) cachedPopup;
    }

    public void show() {
        asPopup().showPopupWindow();
    }
}
