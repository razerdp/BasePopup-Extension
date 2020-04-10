package com.razerdp.basepopup.extension.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.razerdp.basepopup.extension.PopupProviders;
import com.razerdp.basepopup.extension.alert.AlertInterface;
import com.razerdp.basepopup.extension.alert.AlertParams;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tvTest = (TextView) findViewById(R.id.tv_test);
        tvTest.setOnClickListener(v -> show());
    }

    private void show() {
        PopupProviders.alert(this)
                .setStyle(AlertParams.AlertStyle.IOS)
                .setTitle("测试")
                .setMessage("测试测试")
                .setOnClickListener(new AlertInterface.OnClickListener() {
                    @Override
                    public void onPositiveClick(View v) {
                        super.onPositiveClick(v);
                    }

                    @Override
                    public void onNegativeClick(View v) {
                        super.onNegativeClick(v);
                    }

                    @Override
                    public void onNeutralClick(View v) {
                        super.onNeutralClick(v);
                    }
                })
                .setPositiveText("pos")
                .setNeutralText("neutral")
                .setNegativeText("negat")
                .asPopup()
                .setBlurBackgroundEnable(true)
                .showPopupWindow();

    }
}
