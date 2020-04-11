package com.razerdp.basepopup.extension.alert;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.razerdp.basepopup.extension.utils.UIHelper;
import com.razerdp.basepopup.extension.utils.ViewUtils;
import com.razerdp.basepopup.lib.R;

import androidx.annotation.NonNull;

/**
 * Created by 大灯泡 on 2020/4/8.
 */
public class IOSAlertPopupWindowAdapter extends BaseAlertPopupWindowAdapter {

    TextView tvTitle;
    TextView tvMessage;
    TextView tvPositive;
    TextView tvNeutral;
    TextView tvNegative;
    LinearLayout layoutButton;

    private static final int[] HORIZONTAL_BUTTON_BACKGROUND_1 = {R.drawable.be_alert_ios_selector_bottom_single};
    private static final int[] HORIZONTAL_BUTTON_BACKGROUND_2 = {R.drawable.be_alert_ios_selector_bottom_left,
            R.drawable.be_alert_ios_selector_bottom_right};
    private static final int[] HORIZONTAL_BUTTON_BACKGROUND_3 = {R.drawable.be_alert_ios_selector_bottom_left,
            R.drawable.be_alert_ios_selector_bottom_middle,
            R.drawable.be_alert_ios_selector_bottom_right};
    private static final int[] VERTICAL_BUTTON_BACKGROUND_1 = {R.drawable.be_alert_ios_selector_bottom_single};
    private static final int[] VERTICAL_BUTTON_BACKGROUND_2 = {R.drawable.be_alert_ios_selector_bottom_middle,
            R.drawable.be_alert_ios_selector_bottom_single};
    private static final int[] VERTICAL_BUTTON_BACKGROUND_3 = {R.drawable.be_alert_ios_selector_bottom_middle,
            R.drawable.be_alert_ios_selector_bottom_middle,
            R.drawable.be_alert_ios_selector_bottom_single};

    IOSAlertPopupWindowAdapter(@NonNull AlertParams p) {
        super(p);
    }

    @Override
    protected View onCreateContentView(ViewGroup parent) {
        View contentView = inflateView(parent, R.layout.be_alert_ios);
        onInitView(contentView);
        return contentView;
    }


    @Override
    public void onAttachPopupWindow(@NonNull AlertPopupWindow basePopupWindow) {

    }

    private void onInitView(View contentView) {
        tvTitle = contentView.findViewById(R.id.be_alert_ios_title);
        tvMessage = contentView.findViewById(R.id.be_alert_ios_message);
        layoutButton = contentView.findViewById(R.id.be_alert_ios_layout_button);
        tvPositive = contentView.findViewById(R.id.be_alert_ios_btn_positive);
        tvNeutral = contentView.findViewById(R.id.be_alert_ios_btn_neutral);
        tvNegative = contentView.findViewById(R.id.be_alert_ios_btn_negative);
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

        ViewUtils.setViewsVisible(TextUtils.isEmpty(p.message) ? View.GONE : View.VISIBLE, tvMessage);

        final int showButtonCount = p.getAlertButtonShowCount();
        int selectBackgroundIndex = 0;
        ButtonBackgroundSetter:
        for (int i = 0; i < layoutButton.getChildCount(); i++) {
            View v = layoutButton.getChildAt(i);
            if (v.getVisibility() == View.VISIBLE) {
                switch (showButtonCount) {
                    case 0:
                        break ButtonBackgroundSetter;
                    case 1:
                        v.setBackgroundResource(p.buttonOrientation == AlertParams.HORIZONTAL ?
                                HORIZONTAL_BUTTON_BACKGROUND_1[selectBackgroundIndex] :
                                VERTICAL_BUTTON_BACKGROUND_1[selectBackgroundIndex]);
                        break ButtonBackgroundSetter;
                    case 2:
                        v.setBackgroundResource(p.buttonOrientation == AlertParams.HORIZONTAL ?
                                HORIZONTAL_BUTTON_BACKGROUND_2[selectBackgroundIndex++] :
                                VERTICAL_BUTTON_BACKGROUND_2[selectBackgroundIndex++]);
                        break;
                    case 3:
                        v.setBackgroundResource(p.buttonOrientation == AlertParams.HORIZONTAL ?
                                HORIZONTAL_BUTTON_BACKGROUND_3[selectBackgroundIndex++] :
                                VERTICAL_BUTTON_BACKGROUND_3[selectBackgroundIndex++]);
                        break;
                }
            }
        }
    }

    private void onButtonLayoutOrientationChange() {
        if (layoutButton.getOrientation() == p.buttonOrientation) {
            return;
        }
        View[] viewsBucket = new View[layoutButton.getChildCount()];
        for (int i = 0; i < viewsBucket.length; i++) {
            View child = layoutButton.getChildAt(i);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) child.getLayoutParams();
            params.weight = p.buttonOrientation == AlertParams.HORIZONTAL ? 1 : 0;
            params.width = p.buttonOrientation == AlertParams.HORIZONTAL ? 0 : ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = p.buttonOrientation == AlertParams.VERTICAL ? ViewGroup.LayoutParams.WRAP_CONTENT : ViewGroup.LayoutParams.MATCH_PARENT;
            viewsBucket[i] = child;
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
