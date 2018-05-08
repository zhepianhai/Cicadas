package com.zph.cicadas.adpter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.DefaultLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.zph.cicadas.R;
import com.zph.cicadas.frag.home.FragHome;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author zph
 * @date 2018/5/8
 */

public class AdpVirtualLayout extends VirtualLayoutAdapter<AdpVirtualLayout.VH> {

    private VirtualLayoutManager mManager;
    private Context mContext;
    private ArrayList<HashMap<String, String>> mArrayListData;
    private LayoutInflater mInflater;

    public AdpVirtualLayout(@NonNull VirtualLayoutManager layoutManager, Context mContext, ArrayList<HashMap<String, String>> mArrayListData) {
        super(layoutManager);
        this.mManager = layoutManager;
        this.mContext = mContext;
        this.mArrayListData = mArrayListData;
        this.mInflater = LayoutInflater.from(mContext);
        this.initLayoutHelped();
    }

    private void initLayoutHelped() {
        List<LayoutHelper> helpers = new LinkedList<>();

        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        gridLayoutHelper.setItemCount(9);

        FloatLayoutHelper floatLayoutHelper =
                new FloatLayoutHelper(
                );
        floatLayoutHelper.setItemCount(1);

        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
        columnLayoutHelper.setItemCount(5);
        columnLayoutHelper.setWeights(new float[]{30, 10, 30, 20, 10});
        columnLayoutHelper.setMarginBottom(100);

        //设置线性布局
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setItemCount(2);
        linearLayoutHelper.setDividerHeight(10);

        helpers.add(linearLayoutHelper);
        helpers.add(floatLayoutHelper);
        helpers.add(columnLayoutHelper);
        helpers.add(gridLayoutHelper);
        helpers.add(DefaultLayoutHelper.newHelper(2));
        this.mManager.setLayoutHelpers(helpers);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(mInflater.inflate(R.layout.item_fraghome_vlayout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 420);
        if (position == 2) {
            layoutParams.width = 180;
            layoutParams.height = 180;
            layoutParams.setMargins(10,10,10,10);
        }
        holder.itemView.setLayoutParams(layoutParams);
        holder.mTv.setText(Integer.toString(position));
        if (position == 2) {
            holder.itemView.setBackgroundColor(Color.parseColor("#DB7093"));
        } else
            holder.itemView.setBackgroundColor(Color.parseColor("#A9A9A9"));
    }

    @Override
    public int getItemCount() {
        List<LayoutHelper> helpers = getLayoutHelpers();
        if (helpers == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0, size = helpers.size(); i < size; i++) {
            count += helpers.get(i).getItemCount();
        }
        return count;
    }

    class VH extends RecyclerView.ViewHolder {
        TextView mTv;
        VH(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.item_fraghome_vlayout_tv);
        }
    }
}
