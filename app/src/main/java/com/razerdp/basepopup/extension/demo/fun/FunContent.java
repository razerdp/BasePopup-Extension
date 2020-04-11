package com.razerdp.basepopup.extension.demo.fun;

import android.view.View;

import com.razerdp.basepopup.extension.demo.base.baseadapter.MultiType;


/**
 * Created by 大灯泡 on 2019/9/20
 * <p>
 * Description：
 */
public abstract class FunContent implements MultiType {
    public String title;
    public String desc;
    public String option;

    public FunContent() {
    }


    @Override
    public int getItemType() {
        return 1;
    }

    public abstract void toShow(View v);

    public abstract void toOption(View v);

}
