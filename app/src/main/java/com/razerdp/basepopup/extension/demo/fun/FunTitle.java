package com.razerdp.basepopup.extension.demo.fun;

import com.razerdp.basepopup.extension.demo.base.baseadapter.MultiType;

/**
 * Created by 大灯泡 on 2019/9/20
 * <p>
 * Description：
 */
public class FunTitle implements MultiType {
    public String title;

    public FunTitle(String title) {
        this.title = title;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
