package razerdp.basepopup.extension.utils;

import android.content.res.Resources;
import android.util.TypedValue;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;

import razerdp.basepopup.BasePopupSDK;

/**
 * Created by 大灯泡 on 2020/4/11.
 */
public class UIHelper {

    public static int getColor(@ColorRes int colorResId) {
        return ContextCompat.getColor(BasePopupSDK.getApplication(), colorResId);
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int getThemeColor(int id) {
        final TypedValue value = new TypedValue();
        BasePopupSDK.getApplication().getTheme().resolveAttribute(id, value, true);
        return value.data;
    }
}
