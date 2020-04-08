package com.razerdp.basepopup.extension;

import androidx.annotation.NonNull;
import razerdp.basepopup.BasePopupWindow;

/**
 * Created by 大灯泡 on 2020/4/8.
 * <p>
 * popup工厂
 */
public class PopupProviders {

    public interface Factory {
        @NonNull
        <T extends BasePopupWindow> T create(@NonNull Object parentToken, @NonNull Class<T> popupClass);
    }



}
