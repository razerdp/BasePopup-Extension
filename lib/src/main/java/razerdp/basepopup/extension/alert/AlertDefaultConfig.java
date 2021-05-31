package razerdp.basepopup.extension.alert;

import razerdp.basepopup.extension.ExtensionParams;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

/**
 * Created by 大灯泡 on 2020/4/11.
 */
public abstract class AlertDefaultConfig<T extends AlertDefaultConfig> extends ExtensionParams.DefaultConfig {
    public T setDefaultTitle(CharSequence title) {
        getDefaultParams().setTitle(title);
        return (T) this;
    }

    public T setDefaultMessage(CharSequence title) {
        getDefaultParams().setMessage(title);
        return (T) this;
    }

    public T setDefaultPositiveText(CharSequence text) {
        getDefaultParams().setPositiveText(text);
        return (T) this;
    }

    public T setDefaultNeutralText(CharSequence text) {
        getDefaultParams().setNeutralText(text);
        return (T) this;
    }

    public T setDefaultNegativeText(CharSequence text) {
        getDefaultParams().setNegativeText(text);
        return (T) this;
    }

    public T setDefaultPositiveTextColor(@ColorInt int color) {
        getDefaultParams().setPositiveTextColor(color);
        return (T) this;
    }

    public T setDefaultNeutralTextColor(@ColorInt int color) {
        getDefaultParams().setNeutralTextColor(color);
        return (T) this;
    }

    public T setDefaultNegativeTextColor(@ColorInt int color) {
        getDefaultParams().setNegativeTextColor(color);
        return (T) this;
    }

    public T setDefaultCancelable(boolean cancelable) {
        getDefaultParams().setCancelable(cancelable);
        return (T) this;
    }

    public T setDefaultTitleTextColor(int titleTextColor) {
        getDefaultParams().setTitleTextColor(titleTextColor);
        return (T) this;
    }

    public T setDefaultMessageTextColor(int messageTextColor) {
        getDefaultParams().setMessageTextColor(messageTextColor);
        return (T) this;
    }

    public T setDefaultButtonOrientation(@AlertParams.ButtonOrientation int orientation) {
        getDefaultParams().setButtonOrientation(orientation);
        return (T) this;
    }

    void setDefault(AlertParams p) {
        if (p.title == null) {
            p.title = getDefaultParams().title;
        }
        if (p.message == null) {
            p.message = getDefaultParams().message;
        }
        if (p.positiveText == null) {
            p.positiveText = getDefaultParams().positiveText;
        }
        if (p.neutralText == null) {
            p.neutralText = getDefaultParams().neutralText;
        }
        if (p.negativeText == null) {
            p.negativeText = getDefaultParams().negativeText;
        }
        if (p.positiveTextColor == 0) {
            p.positiveTextColor = getDefaultParams().positiveTextColor;
        }
        if (p.neutralTextColor == 0) {
            p.neutralTextColor = getDefaultParams().neutralTextColor;
        }
        if (p.negativeTextColor == 0) {
            p.negativeTextColor = getDefaultParams().negativeTextColor;
        }
        if (p.titleTextColor == 0) {
            p.titleTextColor = getDefaultParams().titleTextColor;
        }
        if (p.messageTextColor == 0) {
            p.messageTextColor = getDefaultParams().messageTextColor;
        }
    }

    @NonNull
    public abstract AlertParams getDefaultParams();
}

