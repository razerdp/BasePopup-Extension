package razerdp.basepopup.extension.alert;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;

import androidx.fragment.app.Fragment;
import razerdp.basepopup.extension.BasePopupWindowExtension;
import razerdp.basepopup.lib.R;

/**
 * Created by 大灯泡 on 2020/4/8.
 * alert类型
 */
public class AlertPopupWindow extends BasePopupWindowExtension<AlertParams> {
    private static final String TAG = "AlertPopupWindow";

    public AlertPopupWindow(Context context, AlertParams params) {
        super(context, params);
        init();
    }

    public AlertPopupWindow(Fragment fragment, AlertParams params) {
        super(fragment, params);
        init();
    }

    public AlertPopupWindow(Dialog dialog, AlertParams params) {
        super(dialog, params);
        init();
    }

    void init() {
        setPopupGravity(Gravity.CENTER);
        setContentView(R.layout.be_container_wrap);
    }


    @Deprecated
    @Override
    public void showPopupWindow(View anchorView) {
        super.showPopupWindow(anchorView);
    }

    @Deprecated
    @Override
    public void showPopupWindow(int x, int y) {
        super.showPopupWindow(x, y);
    }
}
