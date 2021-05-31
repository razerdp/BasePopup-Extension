package razerdp.basepopup.extension.alert;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import razerdp.basepopup.extension.BasePopupWindowExtensionAdapter;
import razerdp.basepopup.extension.ExtensionParams;
import razerdp.basepopup.extension.utils.UIHelper;
import razerdp.basepopup.lib.R;

/**
 * Created by 大灯泡 on 2020/4/8.
 * <p>
 * 本类只关注一些参数配置，以及事件分配，具体ui渲染等逻辑交由adapter完成
 */
public class AlertParams extends ExtensionParams {
    public enum AlertStyle {
        MATERIAL(new DefaultMaterialAlertConfig()),
        IOS(new DefaultIOSAlertConfig());

        private final AlertDefaultConfig sConfig;

        AlertStyle(@NonNull AlertDefaultConfig sConfig) {
            this.sConfig = sConfig;
        }

        protected <T extends AlertDefaultConfig> T defaultConfig() {
            return (T) sConfig;
        }
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


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({HORIZONTAL, VERTICAL})
    public @interface ButtonOrientation {

    }

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
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
    int titleTextColor;
    @ColorInt
    int messageTextColor;
    @ColorInt
    int positiveTextColor;
    @ColorInt
    int neutralTextColor;
    @ColorInt
    int negativeTextColor;

    int buttonOrientation = HORIZONTAL;
    boolean cancelable = true;
    boolean defaultConfigEnable = true;

    protected AlertParams() {
    }

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
                if (defaultConfigEnable) {
                    type.sConfig.setDefault(this);
                }
                setAdapter(new IOSAlertPopupWindowAdapter(this));
                break;
            case MATERIAL:
                if (defaultConfigEnable) {
                    type.sConfig.setDefault(this);
                }
                setAdapter(new MaterialAlertPopupWindowAdapter(this));
                break;
            default:
                break;
        }
        return this;
    }

    /**
     * 按键
     *
     * @see ButtonMode
     */
    public AlertParams setButtonMode(int mode) {
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

    public AlertParams setTitleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
        return this;
    }

    public AlertParams setMessageTextColor(int messageTextColor) {
        this.messageTextColor = messageTextColor;
        return this;
    }

    public AlertParams setButtonOrientation(@ButtonOrientation int buttonOrientation) {
        this.buttonOrientation = buttonOrientation;
        if (buttonOrientation != VERTICAL && buttonOrientation != HORIZONTAL) {
            throw new IllegalArgumentException("参数必须是VERTICAL或者HORIZONTAL之一");
        }
        return this;
    }

    public AlertParams setDefaultConfigEnable(boolean defaultConfigEnable) {
        this.defaultConfigEnable = defaultConfigEnable;
        return this;
    }

    void onButtonClickInternal(@NonNull AlertPopupWindow popupWindow, @ButtonMode int mode, View v) {
        if (mOnClickListener != null) {
            mOnClickListener.onAlertButtonClickInternal(popupWindow, mode, v);
        }
    }

    int getAlertButtonShowCount() {
        int count;
        switch (buttonMode) {
            case 0:
                count = 0;
                break;
            case MODE_MASK:
                count = 3;
                break;
            case MODE_POSITIVE:
            case MODE_NEUTRAL:
            case MODE_NEGATIVE:
                count = 1;
                break;
            default:
                count = 2;
        }
        return count;
    }

    public AlertPopupWindow asPopup() {
        // TODO: 2020/4/10 优雅的实现它。。。 factory?策略模式？反射？
        if (cachedPopup != null) {
            return (AlertPopupWindow) cachedPopup;
        }
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

    public static class DefaultIOSAlertConfig extends AlertDefaultConfig<DefaultIOSAlertConfig> {
        private static final AlertParams DEFAULT_PARAMS = new AlertParams();

        DefaultIOSAlertConfig() {
            setDefaultTitleTextColor(Color.BLACK);
            setDefaultMessageTextColor(Color.BLACK);
            setDefaultPositiveTextColor(UIHelper.getColor(R.color.be_alert_ios_text_blue));
            setDefaultNeutralTextColor(UIHelper.getColor(R.color.be_alert_ios_text_blue));
            setDefaultNegativeTextColor(UIHelper.getColor(R.color.be_alert_ios_text_red));
        }

        @NonNull
        @Override
        public AlertParams getDefaultParams() {
            return DEFAULT_PARAMS;
        }
    }

    /**
     * https://material.io/develop/android/components/dialogs/
     * https://github.com/material-components/material-components-android/blob/master/docs/components/Button.md#text-label-attributes
     */
    public static class DefaultMaterialAlertConfig extends AlertDefaultConfig<DefaultMaterialAlertConfig> {
        private static final AlertParams DEFAULT_PARAMS = new AlertParams();


        DefaultMaterialAlertConfig() {
            setDefaultTitleTextColor(UIHelper.getColor(R.color.be_alert_material_title_color));
            setDefaultMessageTextColor(UIHelper.getColor(R.color.be_alert_material_message_color));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                setDefaultPositiveTextColor(UIHelper.getThemeColor(android.R.attr.colorPrimary));
                setDefaultNeutralTextColor(UIHelper.getThemeColor(android.R.attr.colorPrimary));
                setDefaultNegativeTextColor(UIHelper.getThemeColor(android.R.attr.colorPrimary));
            } else {
                setDefaultPositiveTextColor(UIHelper.getColor(R.color.be_default_color_primary));
                setDefaultNeutralTextColor(UIHelper.getColor(R.color.be_default_color_primary));
                setDefaultNegativeTextColor(UIHelper.getColor(R.color.be_default_color_primary));
            }
        }

        @NonNull
        @Override
        public AlertParams getDefaultParams() {
            return DEFAULT_PARAMS;
        }
    }
}
