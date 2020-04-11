package com.razerdp.basepopup.extension.demo;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.razerdp.basepopup.extension.demo.base.baseactivity.BaseActivity;
import com.razerdp.basepopup.extension.demo.base.baseadapter.BaseMultiRecyclerViewHolder;
import com.razerdp.basepopup.extension.demo.base.baseadapter.MultiRecyclerViewAdapter;
import com.razerdp.basepopup.extension.demo.base.baseadapter.MultiType;
import com.razerdp.basepopup.extension.demo.base.baseadapter.OnItemClickListener;
import com.razerdp.basepopup.extension.demo.fun.FunContent;
import com.razerdp.basepopup.extension.demo.fun.FunTitle;
import com.razerdp.basepopup.extension.demo.fun.alert.FunIOSAlert;
import com.razerdp.basepopup.extension.demo.utils.ButterKnifeUtil;
import com.razerdp.basepopup.extension.demo.utils.UIHelper;
import com.razerdp.basepopup.extension.demo.widget.DPRecyclerView;
import com.razerdp.basepopup.extension.demo.widget.DPTextView;
import com.razerdp.basepopup.extension.demo.widget.decoration.GridItemDecoration;
import com.razerdp.basepopup.extension.demo.widget.decoration.SpaceOption;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import butterknife.BindView;
import butterknife.OnClick;

public class DemoActivity extends BaseActivity {
    @BindView(R.id.rv_content)
    DPRecyclerView rvContent;

    MultiRecyclerViewAdapter mAdapter;


    @Override
    protected void onHandleIntent(Intent intent) {

    }

    @Override
    public int contentViewLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitView(View decorView) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mAdapter.getItemViewType(position) == 0 ? 2 : 1;
            }
        });
        rvContent.setLayoutManager(gridLayoutManager);
        rvContent.addItemDecoration(new GridItemDecoration(new SpaceOption.Builder().size(UIHelper.DP8).build()));
        mAdapter = new MultiRecyclerViewAdapter(this, createItem());
        mAdapter.appendHolder(InnerTitleViewHolder.class, 0)
                .appendHolder(InnerItemViewHolder.class, 1);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position, Object data) {
                if (data instanceof FunContent) {
                    ((FunContent) data).toShow(v);
                }
            }
        });
        rvContent.setAdapter(mAdapter);

    }

    private List<MultiType> createItem() {
        List<MultiType> result = new ArrayList<>();
        result.add(new FunTitle("弹窗"));
        result.add(new FunIOSAlert());

        return result;
    }


    static class InnerTitleViewHolder extends BaseMultiRecyclerViewHolder<FunTitle> {

        @BindView(R.id.tv_title)
        TextView tvTitle;

        public InnerTitleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnifeUtil.bind(this, itemView);
        }

        @Override
        public int inflateLayoutResourceId() {
            return R.layout.item_fun_title;
        }

        @Override
        public void onBindData(FunTitle data, int position) {
            tvTitle.setText(data.title);
        }
    }


    static class InnerItemViewHolder extends BaseMultiRecyclerViewHolder<FunContent> {

        @BindView(R.id.tv_fun)
        TextView tvFun;
        @BindView(R.id.divider)
        View divider;
        @BindView(R.id.tv_option)
        DPTextView tvOption;

        public InnerItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnifeUtil.bind(this, itemView);
        }

        @Override
        public int inflateLayoutResourceId() {
            return R.layout.item_fun_content;
        }

        @Override
        public void onBindData(FunContent data, int position) {
            tvFun.setText(data.title);
            tvOption.setText(TextUtils.isEmpty(data.option) ? "配置" : data.option);
        }

        @OnClick(R.id.tv_option)
        void showOption(View v) {
            getData().toOption(v);
        }

    }
}