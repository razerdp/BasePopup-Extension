package razerdp.basepopup.extension.alert;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import razerdp.basepopup.extension.utils.UIHelper;
import razerdp.basepopup.extension.utils.ViewUtils;
import razerdp.basepopup.lib.R;

/**
 * Created by 大灯泡 on 2021/5/31.
 */
public class MaterialAlertPopupWindowAdapter extends BaseAlertPopupWindowAdapter {

    TextView tvTitle;
    TextView tvMessage;
    AppCompatButton tvPositive;
    AppCompatButton tvNeutral;
    AppCompatButton tvNegative;
    LinearLayout layoutButton;

    MaterialAlertPopupWindowAdapter(@NonNull AlertParams p) {
        super(p);
    }

    @Override
    protected View onCreateContentView(ViewGroup parent) {
        View contentView = inflateView(parent, R.layout.be_alert_material);
        onInitView(contentView);
        return contentView;
    }


    @Override
    public void onAttachPopupWindow(@NonNull AlertPopupWindow basePopupWindow) {
        basePopupWindow.setBackgroundColor(Color.parseColor("#8faaaaaa"));
    }

    private void onInitView(View contentView) {
        tvTitle = contentView.findViewById(R.id.be_alert_material_title);
        tvMessage = contentView.findViewById(R.id.be_alert_material_message);
        layoutButton = contentView.findViewById(R.id.be_alert_material_layout_button);
        tvPositive = contentView.findViewById(R.id.be_alert_material_btn_positive);
        tvNeutral = contentView.findViewById(R.id.be_alert_material_btn_neutral);
        tvNegative = contentView.findViewById(R.id.be_alert_material_btn_negative);
    }

    @Override
    protected void onPreShow(AlertParams p) {
        super.onPreShow(p);
        if (getPopupWindow() != null) {
            getPopupWindow().setMaxWidth(Math.round(UIHelper.getScreenWidth() * 0.72f));
        }
        onButtonLayoutOrientationChange();

        bindPositiveView(tvPositive);
        bindNeutralView(tvNeutral);
        bindNegativeView(tvNegative);

        tvTitle.setText(p.title);
        tvMessage.setText(p.message);
        tvPositive.setText(p.positiveText);
        tvNeutral.setText(p.neutralText);
        tvNegative.setText(p.negativeText);
        tvPositive.setTextColor(p.positiveTextColor);
        tvNeutral.setTextColor(p.neutralTextColor);
        tvNegative.setTextColor(p.negativeTextColor);

        ViewUtils.setViewsVisible(TextUtils.isEmpty(p.message) ? View.GONE : View.VISIBLE,
                                  tvMessage);
    }

    private void onButtonLayoutOrientationChange() {
        if (layoutButton.getOrientation() == p.buttonOrientation) {
            return;
        }
        View[] viewsBucket = new View[layoutButton.getChildCount()];
        for (int i = 0; i < viewsBucket.length; i++) {
            viewsBucket[i] = layoutButton.getChildAt(i);
        }
        layoutButton.removeAllViewsInLayout();
        if (viewsBucket[0] != tvPositive) {
            swapFirstAndLast(viewsBucket);
        }
        for (View view : viewsBucket) {
            layoutButton.addView(view);
        }
        layoutButton.setOrientation(p.buttonOrientation);
    }

    private void swapFirstAndLast(View[] viewBucket) {
        int len = viewBucket.length;
        if (len <= 0) return;
        View temp = viewBucket[0];
        viewBucket[0] = viewBucket[len - 1];
        viewBucket[len - 1] = temp;
    }

    @Override
    protected void onPositiveViewVisibleChange(boolean isShow) {
        ViewUtils.setViewsVisible(isShow ? View.VISIBLE : View.GONE, tvPositive);
    }

    @Override
    protected void onNeutralViewVisibleChange(boolean isShow) {
        ViewUtils.setViewsVisible(isShow ? View.VISIBLE : View.GONE, tvNeutral);
    }

    @Override
    protected void onNegativeViewVisibleChange(boolean isShow) {
        ViewUtils.setViewsVisible(isShow ? View.VISIBLE : View.GONE, tvNegative);
    }

}
