package razerdp.basepopup.extension;

import android.animation.Animator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

/**
 * Created by 大灯泡 on 2020/4/8.
 * BasePopup扩展Adapter
 */
public abstract class BasePopupWindowExtensionAdapter<P extends BasePopupWindowExtension> {

    private P mPopupWindow;
    private ExtensionParams.DefaultConfig defaultConfig;

    final void attachPopupWindow(@NonNull P basePopupWindow) {
        this.mPopupWindow = basePopupWindow;
        onAttachPopupWindow(basePopupWindow);
    }

    public void onAttachPopupWindow(@NonNull P basePopupWindow) {

    }

    /**
     * 返回您的ContentView
     */
    protected abstract View onCreateContentView(ViewGroup parent);

    /**
     * preShow
     */
    protected abstract void onBeforeShow();

    protected Animation onCreateShowAnimation(int width, int height) {
        return null;
    }

    protected Animator onCreateShowAnimator(int width, int height) {
        return null;
    }

    protected Animation onCreateDismissAnimation(int width, int height) {
        return null;
    }

    protected Animator onCreateDismissAnimator(int width, int height) {
        return null;
    }

    protected View inflateView(ViewGroup parent, @LayoutRes int layoutId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    public P getPopupWindow() {
        return mPopupWindow;
    }
}
