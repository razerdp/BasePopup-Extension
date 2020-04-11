package com.razerdp.basepopup.extension;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;

import com.razerdp.basepopup.extension.utils.ViewUtils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import razerdp.basepopup.BaseLazyPopupWindow;

/**
 * Created by 大灯泡 on 2020/4/8.
 * <p>
 * 本PopupWindow是个壳，实际上的操作委托给各个Adapter
 */
public abstract class BasePopupWindowExtension<P extends ExtensionParams> extends BaseLazyPopupWindow {

    private P mExtensionParams;

    public BasePopupWindowExtension(Context context, P params) {
        super(context);
        this.mExtensionParams = params;
    }

    public BasePopupWindowExtension(Fragment fragment, P params) {
        super(fragment);
        this.mExtensionParams = params;
    }

    public BasePopupWindowExtension(Dialog dialog, P params) {
        super(dialog);
        this.mExtensionParams = params;
    }

    @Override
    public void onViewCreated(@NonNull View contentView) {
        super.onViewCreated(contentView);
        onParamViewCreate(mExtensionParams);
    }


    @Override
    protected Animation onCreateShowAnimation(int width, int height) {
        return mExtensionParams.getExtensionAdapter().onCreateShowAnimation(width, height);
    }

    @Override
    protected Animator onCreateShowAnimator(int width, int height) {
        return mExtensionParams.getExtensionAdapter().onCreateShowAnimator(width, height);
    }

    @Override
    protected Animation onCreateDismissAnimation(int width, int height) {
        return mExtensionParams.getExtensionAdapter().onCreateDismissAnimation(width, height);
    }

    @Override
    protected Animator onCreateDismissAnimator(int width, int height) {
        return mExtensionParams.getExtensionAdapter().onCreateDismissAnimator(width, height);
    }

    @Override
    protected boolean onPreShow() {
        mExtensionParams.getExtensionAdapter().onPreShow();
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mExtensionParams != null) {
            mExtensionParams.onDestroy();
        }
        mExtensionParams = null;
    }

    protected void onParamViewCreate(@NonNull ExtensionParams params) {
        params.getExtensionAdapter().attachPopupWindow(this);
        ViewGroup vg = (ViewGroup) getContentView();
        View v = params.getExtensionAdapter().onCreateContentView(vg);
        ViewParent parent = v.getParent();
        if (parent != vg) {
            vg.addView(ViewUtils.removeFromParent(v, true));
        }
    }

}
