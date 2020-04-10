package com.razerdp.basepopup.extension.alert;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.razerdp.basepopup.lib.R;

import androidx.annotation.NonNull;

/**
 * Created by 大灯泡 on 2020/4/8.
 */
public class IOSAlertPopupWindowAdapter extends BaseAlertPopupWindowAdapter {

    public IOSAlertPopupWindowAdapter(@NonNull AlertParams p) {
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
        TextView tvTitle = contentView.findViewById(R.id.be_alert_ios_title);
        TextView tvMessage = contentView.findViewById(R.id.be_alert_ios_message);
        TextView tvPositive = contentView.findViewById(R.id.be_alert_ios_btn_positive);
        TextView tvNeutral = contentView.findViewById(R.id.be_alert_ios_btn_neutral);
        TextView tvNegative = contentView.findViewById(R.id.be_alert_ios_btn_negative);

        bindPositiveView(tvPositive);
        bindNeutralView(tvNeutral);
        bindNegativeView(tvNegative);

        tvTitle.setText(p.title);
        tvMessage.setText(p.message);
        tvPositive.setText(p.positiveText);
        tvNeutral.setText(p.neutralText);
        tvNegative.setText(p.negativeText);
    }

}
